package com.tikie.file.service;

import com.tikie.file.model.TFile;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
public interface TFileService {

    TFile selectByPrimaryKey(String id);
}
