package com.tikie.file.controller;

import com.tikie.common.ExceptionConstant;
import com.tikie.file.model.FileShare;
import com.tikie.file.service.FileShareService;
import com.tikie.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-31
 */
@RestController
@RequestMapping("/file-share")
public class FileShareController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private FileShareService fileShareService;


    @ApiOperation(value = "插入分享码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileTreeIds", value = "文件树id, 多个用 ','分隔拼接", dataType = "String", paramType = "query", required = true),
            @ApiImplicitParam(name = "code", value = "code", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/shareCode")
    public Result<String> addShareCode(@RequestParam(value = "fileTreeIds") String fileTreeIds, @RequestParam(value = "code") String code){
        if (StringUtils.isBlank(fileTreeIds)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            boolean b = fileShareService.insertSelective(fileTreeIds, code);
            if (b){
                return Result.success("success");
            }
            return Result.fail(ExceptionConstant.TFILE_INSERT_FAIL);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_INSERT_FAIL);
        }
    }

    @ApiOperation(value = "code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code", dataType = "String", paramType = "query", required = true)
    })
    @GetMapping("/code")
    public Result<FileShare> selectByCode(@RequestParam(value = "code") String code){
        if (StringUtils.isBlank(code)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            FileShare fileShare = fileShareService.selectByCode(code);
            logger.info("selectFileTreeById@exec:{}",fileShare);
            return Result.success(fileShare);
        }catch (Exception e){
            logger.error("insertSelective@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }

    @ApiOperation(value = "return code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileTreeIds", value = "fileTreeIds", dataType = "String", paramType = "query", required = true)
    })
    @PostMapping("/fileTreeIds")
    public Result<Object> showCode(@RequestParam(value = "fileTreeIds") String fileTreeIds){
        if (StringUtils.isBlank(fileTreeIds)){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        try {
            String s = fileShareService.showCode(fileTreeIds);
            Map<String,Object> map = new HashMap<>();
            map.put("code",s);
            logger.info("showCode@exec:{}",map);
            return Result.success(map);
        }catch (Exception e){
            logger.error("showCode@err:{}",e);
            return Result.fail(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }
}
