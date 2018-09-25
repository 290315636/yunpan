package com.tikie.file.controller;

import com.tikie.common.ExceptionConstant;
import com.tikie.file.model.FileTree;

import com.tikie.file.service.FileTreeService;
import com.tikie.file.service.TFileService;
import com.tikie.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-25
 */
@RestController
@RequestMapping("/file-tree")
public class FileTreeController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileTreeService fileTreeService;

    @Autowired
    TFileService tFileService;


    @ApiOperation(value = "树形文件展示左侧分类")
    @ApiImplicitParams({
    })
    @PostMapping("/left-count")
    public Result<Map<String, Object>> getFileCountMap(){
        Map<String, Object> map = null;
        try {
            map = fileTreeService.getFileCountMap();
            logger.info("getFileCountMap@exec:{}",map);
            return Result.success(map);
        }catch (Exception e){
            logger.error("getFileCountMap@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "树形文件展示 全部")
    @ApiImplicitParams({
    })
    @GetMapping("/list")
    public Result<List<FileTree>> selectListTreeByAll(){
        List<FileTree> list = null;
        try {
            list = fileTreeService.selectListTreeByAll();
            logger.info("selectListTreeByAll@exec:{}",list);
            return Result.success(list);
        }catch (Exception e){
            logger.error("selectListTreeByAll@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "拿到一条树形文件记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "树形文件 id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/findFile")
    public Result<FileTree> selectFileTreeById(@RequestParam(value = "id") String id){
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        FileTree fileTree = null;
        try {
            fileTree = fileTreeService.selectFileTreeById(id);
            logger.info("selectFileTreeById@exec:{}",fileTree);
            return Result.success(fileTree);
        }catch (Exception e){
            logger.error("selectFileTreeById@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "拿到一条树形文件夹记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pid", value = "树形文件 pid", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/findFolder")
    public Result<List<FileTree>> selectFileTreeByPid(@RequestParam(value = "pid") String pid){
        if (StringUtils.isBlank(pid)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        List<FileTree> list = null;
        try {
            list = fileTreeService.selectFileTreeByPid(pid);
            logger.info("selectFileTreeByPid@exec:{}",list);
            return Result.success(list);
        }catch (Exception e){
            logger.error("selectFileTreeByPid@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }
    
    @ApiOperation(value = "拿到一条树形文件夹记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "树形文件 type", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/findFolderByType")
    public Result<List<FileTree>> selectFileTreeByType(@RequestParam(value = "type") String type){
        if (StringUtils.isBlank(type)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        List<FileTree> list = null;
        try {
            list = fileTreeService.selectFileTreeByType(type);
            logger.info("selectFileTreeByType@exec:{}",list);
            return Result.success(list);
        }catch (Exception e){
            logger.error("selectFileTreeByType@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "搜索")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "树形文件 name", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/search")
    public Result<List<FileTree>> selectFileTreeByName(@RequestParam(value = "name") String name){
        if (StringUtils.isBlank(name)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        List<FileTree> list = null;
        try {
            list = fileTreeService.selectFileTreeByName(name);
            logger.info("selectFileTreeByName@exec:{}",list);
            return Result.success(list);
        }catch (Exception e){
            logger.error("selectFileTreeByName@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "将文件放入回收站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "树形文件 id", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/inputReback")
    public Result<Object> deleteFileTreeByOneId(@RequestParam(value = "id") String id){
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean delete = fileTreeService.deleteFileTreeByOneId(id);
            if (delete){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }catch (Exception e){
            logger.error("deleteFileTreeByOneId@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }
    }

    @ApiOperation(value = "重命名一条树形文件记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "树形文件 id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "树形文件 name", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/rename")
    public Result<Object> renameFileTreeByOneId(@RequestParam(value = "id") String id, @RequestParam(value = "name") String name){
        if (StringUtils.isBlank(id) && StringUtils.isBlank(name)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean rename = fileTreeService.reanameFileTreeByOneId(id, name);
            if (rename){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_UPDATE_FAIL);
        }catch (Exception e){
            logger.error("renameFileTreeByOneId@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_UPDATE_FAIL);
        }
    }

    @ApiOperation(value = "新建文件夹")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "name", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pid", value = "pid", dataType = "String", paramType = "query")
    })
    @PostMapping("/addFolder")
    public Result<String> createNewFolder(@RequestParam(value = "name") String name, @RequestParam(value = "pid",defaultValue = "#") String pid) {
        if (StringUtils.isBlank(name)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            String folderId = fileTreeService.createNewFolder(name, pid);
            return Result.success(folderId);
        }catch (Exception e){
            logger.error("createNewFolder@err:{}", e.getMessage());
            return Result.fail(ExceptionConstant.TFILE_INSERT_FAIL);
        }
    }

    @ApiOperation(value = "复制文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pid", value = "pid", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/copy")
    public Result<String> copyFile(@RequestParam(value = "id") String id, @RequestParam(value = "pid") String pid) {
        if (StringUtils.isBlank(id) && StringUtils.isBlank(pid)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean copyFile = fileTreeService.copyFile(id, pid);
            if (copyFile){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_COPY_FAIL);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_COPY_FAIL);
        }
    }

    @ApiOperation(value = "移动文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "pid", value = "pid", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/removeFile")
    public Result<String> removeFile(@RequestParam(value = "id") String id, @RequestParam(value = "pid") String pid) {
        if (StringUtils.isBlank(id) && StringUtils.isBlank(pid)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean copyFile = fileTreeService.removeFile(id, pid);
            if (copyFile){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_REMOVE_FAIL);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_REMOVE_FAIL);
        }
    }

    @ApiOperation(value = "将回收站文件还原")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query", required = true),
    })
    @PostMapping("/reback")
    public Result<String> rebackFile(@RequestParam(value = "id") String id) {
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean copyFile = fileTreeService.removeFile(id,null);
            if (copyFile){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_REMOVE_FAIL);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_REMOVE_FAIL);
        }
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件id", dataType = "String", required = true)
    })
    @DeleteMapping(value="/delete")
    public Result<String> deleteFile(String id) {
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean remove = fileTreeService.delete(id);
            if (remove){
                return Result.success("success");
            }
            logger.info("deleteFile@exec:{}",remove);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }catch (Exception e){
            logger.error("deleteFile@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }
    }

    @ApiOperation(value = "详细信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/detail")
    public Result<Map<String,Object>> detail(@RequestParam(value = "id") String id){
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            Map<String,Object> map = fileTreeService.detail(id);
            logger.info("detail@exec:{}",map);
            return Result.success(map);
        }catch (Exception e){
            logger.error("detail@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "下载指定文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileId", value = "id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/downloads")
    public Result<Object> downloads(String fileId, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isBlank(fileId)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            fileTreeService.downloads(fileId, request, response);
            logger.info("downloads@exec:{}","success");
            return Result.success("success");
        }catch (Exception e){
            logger.error("downloads@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    // 文件下载相关代码
    @ApiOperation(value = "下载指定文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/shared/{id}")
    public String downloadFile(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) {

        String fileName = fileTreeService.selectFileTreeById(id).getName();// 设置文件名，根据code替换成要下载的文件名 TODO
        if (fileName != null) {
            //设置文件路径
            String realPath = tFileService.selectByPrimaryKey(fileTreeService.selectFileTreeById(id).getFileId()).getPath();
            File file = new File(realPath , fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
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
}
