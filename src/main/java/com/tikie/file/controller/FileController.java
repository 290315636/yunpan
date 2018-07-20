/**
 * 
 * 项目名称：tikie-bootstrap-fileupload
 * 创建日期：2018年7月11日
 * 修改历史：
 * 		1、[2018年7月11日]创建文件 by zhaocs
 */
package com.tikie.file.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.tikie.common.CommonEnums;
import com.tikie.common.JsonResult;
import com.tikie.util.DateUtil;
import com.tikie.util.FileSizeUtil;
import com.tikie.util.FileUtil;
import com.tikie.util.MD5Util;
import com.tikie.util.UUIDUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zhaocs
 *
 */
@Controller
@RequestMapping("/file")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class);
    
    @Value("${tikie.project.developer.name}")
    private String developerName;
    
    @Value("${tikie.project.upload.path}")
    private String fileuploadPath;
    
    @ApiOperation(value="跳转到文件上传页面", notes="页面使用thymeleaf渲染")
    @RequestMapping(value="/index", method=RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("file/index");
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
    public JsonResult upload(HttpServletRequest request, HttpServletResponse response){
    	
        //判断保存文件的路径是否存在
    	String baseFilePath = fileuploadPath + DateUtil.getCurrentTime("yyyyMM") + File.separator;
    	baseFilePath = baseFilePath.replace("/", File.separator).replace("\\", File.separator);
        File fileUploadPath = new File(baseFilePath);
        if (!fileUploadPath.exists()) {
            fileUploadPath.mkdirs();
        }
        
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> map =multipartRequest.getFileMap();
        List<Map<String, Object>> handle = new ArrayList<>();
        for (MultipartFile item : map.values()) {
            String fileName = item.getOriginalFilename();    // 当前上传文件全名称
            String fileType = item.getContentType();         // 当前上传文件类型
            String addr = baseFilePath + fileName;         // 保存到服务器目录的文件全路径
            
            long size = item.getSize();
            logger.info("文件名称：{}", fileName);
            logger.info("文件类型：{}", fileType);
            logger.info("文件物理地址：{}", addr);
            logger.info("文件大小：{}", FileSizeUtil.FormetFileSize(size,FileSizeUtil.SIZETYPE_MB) + "Mb");
            File savedFile = new File(baseFilePath, fileName);
            String md5 = MD5Util.getFileMD5(savedFile);
            logger.info("文件md5：{}", md5);
            // 查询是否存在MD5 TODO
            Boolean hasMd5 = false;
            
            // 文件已存在,不需要保存文件
            if(hasMd5) {
            	// 更新文件树到数据库 TODO
            	
            	continue;
            }
            
            // 需要保存文件
            // 查询目录下是否存在同名文件
            Boolean hasFile = FileUtil.checkFileIsExists(addr);
            if(!hasFile) {// 不存在同名文件
            	try {
                    item.transferTo(savedFile);// 保存
                    // 更新文件数据到数据库 TODO
                    
                } catch (IOException | IllegalStateException e) {
                    logger.error(e.getMessage());
                    Map<String, Object> failedFile = new HashMap<>();
                    failedFile.put("name", fileName);
                    failedFile.put("size", FileSizeUtil.FormetFileSize(size,FileSizeUtil.SIZETYPE_MB) + "Mb"); // 转化单位
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
                // 更新文件数据到数据库 TODO
                
            } catch (IOException | IllegalStateException e) {
                logger.error(e.getMessage());
                Map<String, Object> failedFile = new HashMap<>();
                failedFile.put("name", fileName);
                failedFile.put("size", size); // 转化单位
                handle.add(failedFile);
                continue;
            }
            
            // 更新文件树 到数据库 TODO
            
        }
        
        // 更新返回数据
        return new JsonResult(CommonEnums.StatusCode.SUCCESS.getCode(), 
        		CommonEnums.StatusCode.SUCCESS.getMessage(), 
        		handle);
    }
    
    // 文件下载相关代码
    @RequestMapping("/shared/{code}")
    public String downloadFile(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "aim_test.txt";// 设置文件名，根据code替换成要下载的文件名 TODO
        if (fileName != null) {
            //设置文件路径
            String realPath = "D:/aim/";
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
