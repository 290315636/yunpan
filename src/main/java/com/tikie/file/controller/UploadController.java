/**
 * 
 * 项目名称：tikie-bootstrap-fileupload
 * 创建日期：2018年7月11日
 * 修改历史：
 * 		1、[2018年7月11日]创建文件 by zhaocs
 */
package com.tikie.file.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tikie.quartz.service.HelloService;
import com.tikie.util.DateUtil;
import com.tikie.util.MD5Util;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zhaocs
 *
 */
@Controller
@RequestMapping("/file")
public class UploadController {
    private static Logger logger = LoggerFactory.getLogger(UploadController.class);
    
    @Value("${tikie.project.developer.name}")
    private String developerName;
    
    @Value("${tikie.project.fileupload.path}")
    private String fileuploadPath;
    
    @ApiOperation(value="跳转到文件上传页面", notes="页面使用thymeleaf渲染")
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }
    
    @ApiOperation(value="测试返回json数据", notes="返回json")
    @ApiImplicitParam(name = "name", value = "欢迎用户名称", required = false, dataType = "String")
    @RequestMapping(value="/hello", method=RequestMethod.GET)
    @ResponseBody
    public String hello(String name) {
        if(StringUtils.isBlank(name)) {
            return "hello " + developerName;
        }else {
            return "hello " + name;
        }
    }
    
    @ApiIgnore
    @ApiOperation(value="文件上传接口", notes="支持多文件上传")
    @ResponseBody
    @RequestMapping(value = "/upload" ,method = RequestMethod.POST)
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response){

       //判断保存文件的路径是否存在
        File fileUploadPath = new File(fileuploadPath);
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdirs();
        }
         
        Map<String, Object> json = new HashMap<>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        
        Map<String, MultipartFile> map =multipartRequest.getFileMap();
        for (MultipartFile item : map.values()) {
            String fileName = "";        // 当前上传文件全名称
            String fileType = "";        // 当前上传文件类型
            String saveFileName = "";    // 保存到服务器目录的文件名称
            String reportAddr = "";      // 保存到服务器目录的文件全路径
            try {
                fileName = item.getOriginalFilename();
                fileType = item.getContentType();
                
                saveFileName = DateUtil.getCurrentTime("yyyyMMddHHmmss") + "_" + fileName;
                reportAddr = fileUploadPath + saveFileName;
                reportAddr = reportAddr.replace("/", File.separator).replace("\\", File.separator);
                
                File savedFile = new File(fileUploadPath, saveFileName);
                item.transferTo(savedFile);
               
                json.put("newVersionName", "");
                json.put("fileMd5", MD5Util.getFileMD5(savedFile));
                json.put("message", "应用上传成功");
                json.put("status", true);
                json.put("filePath", reportAddr);
                return json;
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
        
        return json;
    }
    
    @ApiOperation(value="测试quzrtz启动定时任务", notes="没事实现任务存储到数据库，在内存存储")
    @GetMapping("/haveProperties")
    @ResponseBody
    public String testQuartz(String name) throws SchedulerException, InterruptedException {
        HelloService helloService = new HelloService();
        helloService.haveProperties();
        return "使用配置文件-定时器开始执行，请观察控制台";
    }
    
}
