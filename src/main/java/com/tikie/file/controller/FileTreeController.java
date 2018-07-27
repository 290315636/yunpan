package com.tikie.file.controller;

import com.github.pagehelper.PageInfo;
import com.tikie.common.ExceptionConstant;
import com.tikie.file.model.FileTree;
import com.tikie.file.model.SuperTreeVo;
import com.tikie.file.model.TFile;
import com.tikie.file.service.FileTreeService;
import com.tikie.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author zhangshitai
 * @date 2018-07-25
 */
@RestController
@RequestMapping("/show")
public class FileTreeController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileTreeService fileTreeService;


    @ApiOperation(value = "树形文件展示 顶级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示数量", dataType = "Long", paramType = "query")
    })
    @GetMapping("/list")
    public Result<PageInfo<SuperTreeVo>> selectListTreeBySuper(@RequestParam(value = "pageNo", defaultValue = "0") int pageNo, @RequestParam(value = "pageSize",defaultValue = "0") int pageSize){
        if ("".equals(pageNo +"") && "".equals(pageSize + "")){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        PageInfo<SuperTreeVo> pageInfo = null;
        try {
            pageInfo = fileTreeService.selectListTreeBySuper(pageNo, pageSize);
            logger.info("selectListTreeBySuper@exec:{}",pageInfo);
            return Result.success(pageInfo);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectListTreeBySuper@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "拿到一条树形文件记录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "树形文件 id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/find")
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
            e.printStackTrace();
            logger.error("selectFileTreeById@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "将文件放入回收站")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "树形文件 id", dataType = "String", paramType = "query", required = true)
    })
    @PutMapping("/reback")
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
    @PostMapping("/folder")
    public Result<String> createNewFolder(@RequestParam(value = "name") String name, @RequestParam(value = "pid",defaultValue = "#") String pid) {
        if (StringUtils.isBlank(name)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean folder = fileTreeService.createNewFolder(name, pid);
            if (folder){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_INSERT_FAIL);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
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

    @ApiOperation(value = "物理删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件id", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping("/delete")
    public Result<String> removeFile(@RequestParam(value = "id") String id) {
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean remove = fileTreeService.delete(id);
            if (remove){
                return Result.success("success");
            }
            logger.info("removeFile@exec:{}",remove);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }catch (Exception e){
            logger.error("removeFile@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }
    }
}
