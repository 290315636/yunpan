package com.tikie.file.dao;

import com.tikie.file.model.FileStatisticDTO;

public interface FileStatisticMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileStatisticDTO record);

    int insertSelective(FileStatisticDTO record);

    FileStatisticDTO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileStatisticDTO record);

    int updateByPrimaryKey(FileStatisticDTO record);
}