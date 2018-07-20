package com.tikie.file.mapper;

import com.tikie.file.model.FileTree;

public interface FileTreeMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileTree record);

    int insertSelective(FileTree record);

    FileTree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileTree record);

    int updateByPrimaryKey(FileTree record);
}