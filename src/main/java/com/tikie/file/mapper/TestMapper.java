package com.tikie.file.mapper;

import com.github.pagehelper.Page;
import com.tikie.file.model.Test;

public interface TestMapper {
    int deleteByPrimaryKey(String id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);

    Page<Test> findByPage(Test record);
}