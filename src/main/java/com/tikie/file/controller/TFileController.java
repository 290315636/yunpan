package com.tikie.file.controller;

import com.tikie.common.ExceptionConstant;
import com.tikie.file.model.TFile;
import com.tikie.file.service.TFileService;
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
 * @date 2018-07-23
 */
@RestController
@RequestMapping("/tfile")
public class TFileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private TFileService tFileService;


    @ApiOperation(value = "文件删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件id", dataType = "String", paramType = "query", required = true)
    })
    @DeleteMapping("/delete")
    public Result<String> deleteByPrimaryKey(@RequestParam(value = "id") String id) {
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean remove = tFileService.deleteByPrimaryKey(id);
            if (remove){
                return Result.success("success");
            }
            logger.info("deleteByPrimaryKey@exec:{}",remove);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }catch (Exception e){
            logger.error("deleteByPrimaryKey@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_DELETE_FAIL);
        }
    }

    @ApiOperation(value = "文件新增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tFile", value = "name,thumbnail,size,path,ctime,utime,type", dataType = "TFile", paramType = "body", required = true)
    })
    @PostMapping("/add")
    public Result<String> insertSelective(@RequestBody @Valid TFile tFile) {
        try {
            boolean insert = tFileService.insertSelective(tFile);
            if (insert){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_INSERT_FAIL);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_INSERT_FAIL);
        }
    }

    @ApiOperation(value = "文件查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "文件 id", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/find")
    public Result<TFile> selectByPrimaryKey(@RequestParam(value = "id") String id) {
        if (StringUtils.isBlank(id)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            TFile tFile = tFileService.selectByPrimaryKey(id);
            return Result.success(tFile);
        }catch (Exception e){
            logger.error("selectByPrimaryKey@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "文件修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tFile", value = "name,thumbnail,size,path,ctime,utime,type", dataType = "TFile", paramType = "body", required = true)
    })
    @PutMapping("/fix")
    public Result<Object> updateByPrimaryKeySelective(@RequestBody @Valid TFile tFile) {
        if (null == tFile){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean update = tFileService.updateByPrimaryKeySelective(tFile);
            if (update){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_UPDATE_FAIL);
        }catch (Exception e){
            logger.error("updateByPrimaryKeySelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_UPDATE_FAIL);
        }

    }
}
