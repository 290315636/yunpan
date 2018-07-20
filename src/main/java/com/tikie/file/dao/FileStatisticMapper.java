package com.tikie.file.dao;

import com.tikie.file.model.FileStatistic;

public interface FileStatisticMapper {
    int deleteByPrimaryKey(String id);

    int insert(FileStatistic record);

    int insertSelective(FileStatistic record);

    FileStatistic selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FileStatistic record);

    int updateByPrimaryKey(FileStatistic record);
}