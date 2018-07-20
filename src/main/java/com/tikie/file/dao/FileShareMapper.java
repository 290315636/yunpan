package com.tikie.file.dao;

import com.tikie.file.model.FileShareDTO;

public interface FileShareMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileShareDTO record);

    int insertSelective(FileShareDTO record);

    FileShareDTO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileShareDTO record);

    int updateByPrimaryKey(FileShareDTO record);
}