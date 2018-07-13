/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月13日
 * 修改历史：
 * 		1、[2018年7月13日]创建文件 by zhaocs
 */
package com.tikie.file.service;

import com.tikie.file.bean.Test;

/**
 * @author zhaocs
 *
 */
public interface TestService {
    Boolean deleteByPrimaryKey(String id);

    Boolean insert(Test record);

    Boolean insertSelective(Test record);

    Test selectByPrimaryKey(String id);

    Boolean updateByPrimaryKeySelective(Test record);

    Boolean updateByPrimaryKey(Test record);
}
