package com.tikie.file.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.tikie.file.model.FileTree;
import com.tikie.file.model.SuperTreeVo;
import com.tikie.file.model.TFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author zhangshitai
 * @date 2018-07-23
 */
public interface FileTreeService {
    Boolean insert(FileTree record);

    Boolean insertSelective(FileTree record);

    Boolean delete(String id);

    Boolean uploadFile(Map<String, MultipartFile> files, String baseFilePath, String pid, String md5);

    List<FileTree> selectListTreeBySuper();

    List<FileTree> selectListTreeByAll();

    FileTree selectFileTreeById(String id);

    List<FileTree> selectFileTreeByPid(String pid);

    List<FileTree> selectFileTreeByName(String name);

    Boolean deleteFileTreeByOneId(String id);

    Boolean reanameFileTreeByOneId(String id, String name);

    Boolean createNewFolder(String name, String pid);

    Boolean copyFile(String id, String pid);

    Boolean removeFile(String id, String pid);

    Map<String,Object> detail(String id);

    void downloads(String fileId, HttpServletRequest request, HttpServletResponse response);
}
