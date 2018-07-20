package com.tikie.file.controller;

import com.tikie.file.model.TFile;
import com.tikie.file.service.TFileService;
import com.tikie.util.DownloadUtil;
import com.tikie.util.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
public class DownloadController {

    @Autowired
    TFileService tFileService;
    // 下载指定文件
    @SuppressWarnings("deprecation")
    @RequestMapping("downloads")
    public void downloads(String fileId, HttpServletRequest request, HttpServletResponse response) {

        String[] fileIds = fileId.split(",");
        // 打包路径
        String realPath = request.getRealPath("/");
        String[] filePath = new String[fileIds.length];

        for (int i = 0; i < fileIds.length; i++) {
            String id = fileIds[i];
            TFile tFile = tFileService.selectByPrimaryKey(id);
            // 原文件
            String srcFile = tFile.getPath() + tFile.getName() + "." + tFile.getType();
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
            filePath[i] = destPath + tFile.getName() + "." + tFile.getType();
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
            DownloadUtil.downloadLocal(downloadFilePath,request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
