package com.tikie.file.dao;

import com.github.pagehelper.Page;
import com.tikie.file.model.FileTree;
import com.tikie.file.model.SuperTreeVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileTreeMapper {

    int insert(FileTree record);

    int insertSelective(FileTree record);

    int delete(String id);

    List<FileTree> selectTreeSelective(FileTree fileTree);

    int deleteFileTreeByOneId(@Param("fileTree") FileTree fileTree);

    int reanameFileTreeByOneId(@Param("fileTree") FileTree fileTree);

}