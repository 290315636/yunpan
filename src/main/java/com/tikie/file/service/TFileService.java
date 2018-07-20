package com.tikie.file.service;

import com.tikie.file.model.TFileDTO;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
public interface TFileService {

    Boolean deleteByPrimaryKey(String id);

    Boolean insert(TFileDTO record);

    Boolean insertSelective(TFileDTO record);

    TFileDTO selectByPrimaryKey(String id);

    Boolean updateByPrimaryKeySelective(TFileDTO record);

    Boolean updateByPrimaryKey(TFileDTO record);
}
