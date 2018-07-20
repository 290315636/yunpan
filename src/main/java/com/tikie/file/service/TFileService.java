package com.tikie.file.service;

import com.tikie.file.model.TFile;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
public interface TFileService {

    Boolean deleteByPrimaryKey(String id);

    Boolean insert(TFile record);

    Boolean insertSelective(TFile record);

    TFile selectByPrimaryKey(String id);

    Boolean updateByPrimaryKeySelective(TFile record);

    Boolean updateByPrimaryKey(TFile record);
}
