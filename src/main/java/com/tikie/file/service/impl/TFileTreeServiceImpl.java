package com.tikie.file.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tikie.file.dao.FileTreeMapper;
import com.tikie.file.model.FileTree;
import com.tikie.file.model.SuperTreeVo;
import com.tikie.file.model.TFile;
import com.tikie.file.model.Test;
import com.tikie.file.service.TFileService;
import com.tikie.file.service.TFileTreeService;
import com.tikie.util.FileSizeUtil;
import com.tikie.util.FileUtil;
import com.tikie.util.MD5Util;
import com.tikie.util.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class TFileTreeServiceImpl implements TFileTreeService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileTreeMapper fileTreeMapper;

    @Resource
    private TFileService tFileService;

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
            logger.info("insertSelective@exec:{}",tree);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("insertSelective@err:{}",e);
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public Boolean uploadFile(Map<String, MultipartFile> files, String baseFilePath , String pid){
        int state = 0;
        List<Map<String, Object>> handle = new ArrayList<>();
        for (MultipartFile item : files.values()) {
            String fileName = item.getOriginalFilename();    // 当前上传文件全名称
            String fileType = item.getContentType();         // 当前上传文件类型
            String addr = baseFilePath + fileName;         // 保存到服务器目录的文件全路径
            long size = item.getSize();                    // 文件大小

            logger.info("文件名称：{}", fileName);
            logger.info("文件类型：{}", fileType);
            logger.info("文件物理地址：{}", addr);
            logger.info("文件大小：{}", FileSizeUtil.FormetFileSize(size, FileSizeUtil.SIZETYPE_MB) + "Mb");

            File savedFile = new File(baseFilePath, fileName);
            String md5 = MD5Util.getFileMD5(savedFile);
            logger.info("文件md5：{}", md5);
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
                tree.setPid("#"); // TODO
                tree.setSize(size);
                state = fileTreeMapper.insertSelective(tree);
                continue;
            }

            fileId = UUIDUtil.getUUID();
            TFile file = new TFile(fileId, fileName, "#", size, addr, fileType, md5);
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
            }
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

            FileTree tree = new FileTree();
            tree.setId(UUIDUtil.getUUID());
            tree.setName(fileName);
            tree.setFileId(fileId);
            tree.setIsFile(true);
            tree.setPid("#");//TODO
            tree.setSize(size);
            // 更新文件树 到数据库
            state = fileTreeMapper.insertSelective(tree);
        }
        return state > 0;
    }

    @Override
    public PageInfo<SuperTreeVo> selectListTreeBySuper(int pageNo, int pageSize) {
        Page<SuperTreeVo> pages = null;
        PageInfo<SuperTreeVo> pageInfo = null;
        try {
            PageHelper.startPage(pageNo, pageSize);
            pages = fileTreeMapper.selectListTreeBySuper();
            pageInfo = new PageInfo<>(pages);
            logger.info("==== selectListTreeBySuper@exec:{} ====", pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== selectListTreeBySuper@err:{} ====", e);
        }
        return pageInfo;
    }
}
