package com.tikie.file.service.impl;

import com.tikie.common.CommonEnums.FileTreeThumbnail;
import com.tikie.common.CommonEnums.MQDestination;
import com.tikie.file.activemq.Producer;
import com.tikie.file.dao.FileTreeMapper;
import com.tikie.file.model.FileTree;
import com.tikie.file.model.TFile;
import com.tikie.file.service.TFileService;
import com.tikie.file.service.FileTreeService;
import com.tikie.util.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class FileTreeServiceImpl implements FileTreeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileTreeMapper fileTreeMapper;

    @Resource
    private TFileService tFileService;

    @Resource
    Producer producer;
    
    @Override
    public Boolean insert(FileTree record) {
        return null;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public Boolean insertSelective(FileTree record) {
        int state = 0;
        try{
            FileTree tree = new FileTree();
            tree.setId(UUIDUtil.getUUID());
            state =  fileTreeMapper.insertSelective(record);
            logger.info("insertSelective@exec:{}", tree);
        }catch (Exception e){
            logger.error("insertSelective@err:{}", e.getMessage());
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public Boolean uploadFile(Map<String, MultipartFile> files, String baseFilePath , String pid, String md5){
        int state = 0;
        List<Map<String, Object>> handle = new ArrayList<>();
        for (MultipartFile item : files.values()) {
            String fileName = item.getOriginalFilename();    // 当前上传文件全名称
            String fileType = item.getContentType();         // 当前上传文件类型
            String addr = baseFilePath + fileName;           // 只做目录下校验是否已存在
            long size = item.getSize();                      // 文件大小
            String thumbnail = StringUtils.EMPTY;
            // 处理缩略图
            for(FileTreeThumbnail nail :FileTreeThumbnail.values()) {
            	if(fileType.toLowerCase().contains(nail.getType())) {
                	thumbnail = nail.getCss();
                }
            }

            File savedFile = new File(baseFilePath, fileName);
//            logger.info("文件md5：{}", md5);
            String fileId = StringUtils.EMPTY;
            Boolean hasMd5 = tFileService.checkMd5FromDB(md5);

            // 文件已存在,不需要保存文件
            if (hasMd5) {
                fileId = tFileService.selectIdByMd5(md5);

                //  更新文件树到数据库
                FileTree tree = new FileTree();
                tree.setId(UUIDUtil.getUUID());
                tree.setIsFile(true);
                tree.setName(fileName);
                tree.setFileId(fileId);
                tree.setPid(pid); 
                tree.setType(fileType.split("/")[0]);
                tree.setThumbnail(thumbnail);
                tree.setSize(FileSizeUtil.FormetFileSize(size, FileSizeUtil.SIZETYPE_MB) + "Mb");
                state = fileTreeMapper.insertSelective(tree);
                producer.send(MQDestination.FILE_QUEUE, tree);
                continue;
            }

            fileId = UUIDUtil.getUUID();
            TFile file = new TFile(fileId, fileName, "#", size, baseFilePath, fileType, md5);
            // 需要保存文件
            // 查询目录下是否存在同名文件
            Boolean hasFile = FileUtil.checkFileIsExists(addr);

            if (!hasFile) {// 不存在同名文件
                try {
                    item.transferTo(savedFile);// 保文件到服务器物理位置
                    // 更新
                    Boolean isSa = tFileService.insertSelective(file);
                    logger.info("insertSelective@exec:{}",isSa);
                } catch (IOException | IllegalStateException e) {
                    logger.error(e.getMessage());
                    Map<String, Object> failedFile = new HashMap<>();
                    failedFile.put("name", fileName);
                    failedFile.put("size", FileSizeUtil.FormetFileSize(size, FileSizeUtil.SIZETYPE_MB) + "Mb"); // 转化单位
                    handle.add(failedFile);
                    continue;
                }
            }else {
            	// 存在同名文件
                String subfix = StringUtils.substringAfterLast(fileName, ".");
                String savedName = UUIDUtil.getUUID() + "." + subfix;
                File savedFil = new File(baseFilePath, savedName);
                try {
                    item.transferTo(savedFil);// 保存
                    // 更新文件数据到数据库
                    file.setName(savedName);
                    Boolean isSa = tFileService.insertSelective(file);
                    logger.info("insertSelective@exec:{}",isSa);
                } catch (IOException | IllegalStateException e) {
                    logger.error(e.getMessage());
                    Map<String, Object> failedFile = new HashMap<>();
                    failedFile.put("name", fileName);
                    failedFile.put("size", size); // 转化单位
                    handle.add(failedFile);
                    continue;
                }
            }
            
            FileTree tree = new FileTree();
            tree.setId(UUIDUtil.getUUID());
            tree.setName(fileName);
            tree.setFileId(fileId);
            tree.setIsFile(true);
            tree.setPid(pid);
            tree.setType(fileType.split("/")[0]);
            tree.setThumbnail(thumbnail);
            tree.setSize(FileSizeUtil.FormetFileSize(size, FileSizeUtil.SIZETYPE_MB) + "Mb");
            // 更新文件树 到数据库
            state = fileTreeMapper.insertSelective(tree);
            producer.send(MQDestination.FILE_QUEUE, tree);
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean delete(String id) {
        int state = 0;
        try{
            state =  fileTreeMapper.delete(id);
            logger.info("removeFile@exec:{}",state);
        }catch (Exception e){
            logger.error("removeFile@err:{}", e.getMessage());
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public List<FileTree> selectListTreeBySuper() {
        List<FileTree> list = null;
        FileTree fileTree = new FileTree();
        try {
            fileTree.setPid("#");
            list = fileTreeMapper.selectTreeSelective(fileTree);
            logger.info("==== selectListTreeBySuper@exec:{} ====", list);
        }catch (Exception e){
            logger.error("==== selectListTreeBySuper@err:{} ====", e.getMessage());
        }
        return list;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public List<FileTree> selectListTreeByAll() {
        List<FileTree> list = null;
        FileTree fileTree = new FileTree();
        try {
            fileTree.setPid("1");
            list = fileTreeMapper.selectTreeSelective(fileTree);
            logger.info("==== selectListTreeBySuper@exec:{} ====", list);
        }catch (Exception e){
            logger.error("==== selectListTreeBySuper@err:{} ====", e.getMessage());
        }
        return list;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public FileTree selectFileTreeById(String id) {
        FileTree fileTree = new FileTree();
        try {
            fileTree.setId(id);
            fileTree = fileTreeMapper.selectTreeSelective(fileTree).get(0);
            logger.info("==== selectFileTreeById@exec:{} ====", fileTree);
        }catch (Exception e){
            logger.error("==== selectFileTreeById@err:{} ====", e.getMessage());
        }
        return fileTree;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public List<FileTree> selectFileTreeByPid(String pid) {
        List<FileTree> list = null;
        FileTree fileTree = new FileTree();
        try {
            fileTree.setPid(pid);
            list = fileTreeMapper.selectTreeSelective(fileTree);
            logger.info("==== selectFileTreeByPid@exec:{} ====", list);
        }catch (Exception e){
            logger.error("==== selectFileTreeByPid@err:{} ====", e.getMessage());
        }
        return list;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public List<FileTree> selectFileTreeByName(String name) {
        List<FileTree> list = null;
        FileTree fileTree = new FileTree();
        try {
            fileTree.setName(name);
            list = fileTreeMapper.selectTreeSelective(fileTree);
            logger.info("==== selectFileTreeByName@exec:{} ====", list);
        }catch (Exception e){
            logger.error("==== selectFileTreeByName@err:{} ====", e.getMessage());
        }
        return list;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean deleteFileTreeByOneId(String id) {
        int state = 0;
        FileTree fileTree = new FileTree();
        try {
            fileTree.setId(id);
            fileTree = fileTreeMapper.selectTreeSelective(fileTree).get(0);
            fileTree.setReback(fileTree.getPid());
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fileTree.setUtime(dateFormat2.format(new Date()));
            state = fileTreeMapper.deleteFileTreeByOneId(fileTree);
            logger.info("==== deleteFileTreeByOneId@exec:{} ====", state);
        }catch (Exception e){
            logger.error("==== deleteFileTreeByOneId@err:{} ====", e.getMessage());
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean reanameFileTreeByOneId(String id, String name) {
        int state = 0;
        FileTree fileTree = new FileTree();
        try {
            fileTree.setId(id);
            fileTree.setName(name);
            state = fileTreeMapper.reanameFileTreeByOneId(fileTree);
            logger.info("==== reanameFileTreeByOneId@exec:{} ====", state);
        }catch (Exception e){
            logger.error("==== reanameFileTreeByOneId@err:{} ====", e.getMessage());
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean createNewFolder(String name, String pid) {
        int state = 0;
        try{
            FileTree fileTree = new FileTree();
            fileTree.setId(UUIDUtil.getUUID());
            fileTree.setIsFile(false);
            fileTree.setName(name);
            fileTree.setPid(pid);
            fileTree.setCtime("now");
            fileTree.setUtime("now");
            fileTree.setThumbnail(FileTreeThumbnail.FOLDER.getCss());
            fileTree.setType(FileTreeThumbnail.FOLDER.getType());
            state =  fileTreeMapper.insertSelective(fileTree);
            logger.info("createNewFolder@exec:{}",state);
        }catch (Exception e){
            logger.error("createNewFolder@err:{}",e);
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean copyFile(String id, String pid) {
        FileTree fileTree = new FileTree();
        int state = 0;
        try{
            fileTree.setId(id);
            fileTree = fileTreeMapper.selectTreeSelective(fileTree).get(0);
            fileTree.setId(UUIDUtil.getUUID());
            fileTree.setPid(pid);
            fileTree.setUtime("now");
            state =  fileTreeMapper.insertSelective(fileTree);
            logger.info("copyFile@exec:{}",state);
        }catch (Exception e){
            logger.error("copyFile@err:{}", e.getMessage());
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean removeFile(String id, String pid) {
        int state = 0;
        FileTree fileTree = new FileTree();
        try{
            fileTree.setId(id);
            fileTree = fileTreeMapper.selectTreeSelective(fileTree).get(0);
            if (pid == null){
                fileTree.setPid(fileTree.getReback());
            }else {
                fileTree.setPid(pid);
            }
            DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            fileTree.setUtime(dateFormat2.format(new Date()));
            state = fileTreeMapper.removeById(fileTree);
            logger.info("copyFile@exec:{}",state);
        }catch (Exception e){
            logger.error("copyFile@err:{}", e.getMessage());
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Map<String,Object> detail(String id) {
        Map<String,Object> map = new HashMap<>();
        FileTree fileTree = new FileTree();
        try {
            fileTree.setId(id);
            fileTree = fileTreeMapper.selectTreeSelective(fileTree).get(0);
            TFile tFile = tFileService.selectByPrimaryKey(fileTree.getFileId());
            map.put("类型",StringUtils.substringAfterLast(fileTree.getName(),".") + "文件");
            map.put("大小",fileTree.getSize());
            map.put("位置",StringUtils.replace(tFile.getPath(),"\\","/"));
            map.put("修改时间",fileTree.getUtime());
            logger.info("==== detail@exec:{} ====", map);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== detail@err:{} ====", e);
        }
        return map;
    }

    // 下载指定文件
    @Override
    public void downloads(String fileId, HttpServletRequest request, HttpServletResponse response) {

        String[] fileIds = fileId.split(",");
        // 打包路径
        String realPath = request.getSession().getServletContext().getRealPath("/");
        String[] filePath = new String[fileIds.length];

        String[] s = new String[fileIds.length];
//        int ma = fileIds.length;
        String name = null;
        for (int i = 0; i < fileIds.length; i++) {
            String id = fileIds[i];
            FileTree fileTree = new FileTree();
            fileTree.setId(id);
            // 文件的id
            String fileId1 = fileTreeMapper.selectTreeSelective(fileTree).get(0).getFileId();
            name = fileTreeMapper.selectTreeSelective(fileTree).get(0).getName();
            TFile tFile = tFileService.selectByPrimaryKey(fileId1);
            // 原文件
            String srcFile = tFile.getPath() + tFile.getName();
            s[i] = tFile.getName();
            // 临时目录
            String destPath = realPath;
            try {
                File file = new File(srcFile);
                //  将文件拷贝到项目根目录
                FileUtils.copyFileToDirectory(file,new File(destPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
            // 文件的临时路径
            filePath[i] = destPath + tFile.getName();;
        }

        String downloadFilePath = filePath[0];

        if (fileIds.length > 1) {
            // 打包
            String zipPath = realPath + "Files.zip";
            ZipUtil.files2Zip(filePath, zipPath);
            downloadFilePath = zipPath;
        }

        // 下载
        try {
            DownloadUtil.downloadLocal(downloadFilePath,name,request, response);
//            downloadFile("500457601.jpg",realPath, request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String downloadFile(String fileName, String realPath,HttpServletRequest request, HttpServletResponse response) {
//        String fileName = "500457601.jpg";// 设置文件名，根据code替换成要下载的文件名 TODO
        if (fileName != null) {
            //设置文件路径
//            String realPath = "E:\\vfs\\yunpan\\201807";
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("multipart/form-data\n");// 设置强制下载不打开
                response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                    System.out.println("success");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }

	@Override
	public Boolean updateFileTreeFolderSize(FileTree record, Boolean isCreat) {
		Assert.assertNotNull(record.getFileId());
		Assert.assertNotNull(record.getPid());
		int state = 0;
		try {
			if(isCreat) {
				state = fileTreeMapper.updateFileTreeAddFolderSize(record);
			}else {
				state = fileTreeMapper.updateFileTreeDelFolderSize(record);
			}
			
			logger.info("updateFileTreeFolderSize@exec:{}", state);
		}catch(Exception e) {
			logger.error("updateFileTreeFolderSize@err:{}", e.getMessage());
		}
		return state >=0;
	}

    public static void main(String[] args) {
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateFormat2.format(new Date()));
    }
}
