package com.tikie.file.dao;

import com.tikie.file.model.TFileDTO;

public interface TFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(TFileDTO record);

    int insertSelective(TFileDTO record);

    TFileDTO selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TFileDTO record);

    int updateByPrimaryKey(TFileDTO record);
}