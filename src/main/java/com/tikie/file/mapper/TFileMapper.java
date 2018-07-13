package com.tikie.file.mapper;

import com.tikie.file.bean.TFile;

public interface TFileMapper {
    int deleteByPrimaryKey(String id);

    int insert(TFile record);

    int insertSelective(TFile record);

    TFile selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TFile record);

    int updateByPrimaryKey(TFile record);
}