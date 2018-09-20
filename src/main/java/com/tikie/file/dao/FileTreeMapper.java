package com.tikie.file.dao;

import com.tikie.file.model.FileTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FileTreeMapper {

    int insert(FileTree record);

    int insertSelective(FileTree record);

    int delete(String id);
    
    FileTree selectByPrimaryKey(String id);
    
    List<FileTree> selectTreeSelective(FileTree fileTree);

    int deleteFileTreeByOneId(@Param("fileTree") FileTree fileTree);

    int removeById(FileTree fileTree);

    int reanameFileTreeByOneId(@Param("fileTree") FileTree fileTree);
    
    // 更新文件夹大小
    int updateFileTreeAddFileSize(String fileId, String pid);
    
    int updateFileTreeDelFileSize(String fileId, String pid);
    
    Set<Map<String, Object>> getFileCountMap();
    
}