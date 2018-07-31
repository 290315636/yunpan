package com.tikie.file.dao;

import com.tikie.file.model.FileShare;

public interface FileShareMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileShare record);

    int insertSelective(FileShare record);

    FileShare selectByPrimaryKey(String id);

    FileShare selectBySelective(FileShare fileShare);

    int updateByPrimaryKeySelective(FileShare record);

    int updateByPrimaryKey(FileShare record);
}