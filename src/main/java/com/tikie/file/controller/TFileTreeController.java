package com.tikie.file.controller;

import com.github.pagehelper.Page;
import com.tikie.common.ExceptionConstant;
import com.tikie.file.model.SuperTreeVo;
import com.tikie.file.service.TFileTreeService;
import com.tikie.util.Result;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zhangshitai
 * @date 2018-07-25
 */
@RestController
@RequestMapping("/list")
public class TFileTreeController {

    private static Logger logger = LoggerFactory.getLogger(FileController.class);

    @Resource
    private TFileTreeService tFileTreeService;

    @ApiOperation(value = "树形文件展示 顶级")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "当前页", dataType = "Long", paramType = "query", required = true),
            @ApiImplicitParam(name = "pageSize", value = "页面大小", dataType = "Long", paramType = "query", required = true)
    })
    @GetMapping("/find")
    public Result<Page<SuperTreeVo>> selectListTreeBySuper(int pageNo, int pageSize){
        if ("".equals(pageNo +"") && "".equals(pageSize + "")){
            return Result.fail(ExceptionConstant.PARAM_IS_NULL);
        }
        Page<SuperTreeVo> lists = null;
        try {
            lists = tFileTreeService.selectListTreeBySuper(pageNo, pageSize);
            logger.info("selectListTreeBySuper@exec:{}",lists);
            return Result.success(lists);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("selectListTreeBySuper@err:{}",e);
            return Result.success(ExceptionConstant.TFILE_SELECT_FAIL);
        }
    }
}
