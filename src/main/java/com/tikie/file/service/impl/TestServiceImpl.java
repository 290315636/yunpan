/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月13日
 * 修改历史：
 * 		1、[2018年7月13日]创建文件 by zhaocs
 */
package com.tikie.file.service.impl;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tikie.file.dao.TestMapper;
import com.tikie.file.model.Test;
import com.tikie.file.service.TestService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocs
 *
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class TestServiceImpl implements TestService{
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TestMapper testMapper;
    
    @Override
    public Boolean deleteByPrimaryKey(String id) {
        int state = 0;
        try {
            state = testMapper.deleteByPrimaryKey(id);
            logger.info("==== deleteByPrimaryKey@exec:{} ====", state);
        } catch (Exception e) {
            logger.error("==== deleteByPrimaryKey@err:{} ====", e);
            throw e;
        }
        return state>0;
    }

    @Override
    public Boolean insert(Test record) {
        int state = 0;
        try {
            state = testMapper.insert(record);
            logger.info("==== insert@exec:{} ====", state);
        } catch (Exception e) {
            logger.error("==== insert@err:{} ====", e);
            throw e;
        }
        return state>0;
    }

    @Override
    public Boolean insertSelective(Test record) {
        int state = 0;
        try {
            state = testMapper.insertSelective(record);
            logger.info("==== insertSelective@exec:{} ====", state);
        } catch (Exception e) {
            logger.error("==== insertSelective@err:{} ====", e);
            throw e;
        }
        return state>0;
    }

    @Override
    public Test selectByPrimaryKey(String id) {
        Test test = null;
        try {
            test = testMapper.selectByPrimaryKey(id);
            logger.info("==== selectByPrimaryKey@exec:{} ====", test);
        } catch (Exception e) {
            logger.error("==== selectByPrimaryKey@err:{} ====", e);
        }
        return test;
    }

    @Override
    public Boolean updateByPrimaryKeySelective(Test record) {
        int state = 0;
        try {
            state = testMapper.updateByPrimaryKeySelective(record);
            logger.info("==== updateByPrimaryKeySelective@exec:{} ====", state);
        } catch (Exception e) {
            logger.error("==== updateByPrimaryKeySelective@err:{} ====", e);
            throw e;
        }
        return state>0;
    }

    @Override
    public Boolean updateByPrimaryKey(Test record) {
        int state = 0;
        try {
            state = testMapper.updateByPrimaryKey(record);
            logger.info("==== updateByPrimaryKey@exec:{} ====", state);
        } catch (Exception e) {
            logger.error("==== updateByPrimaryKey@err:{} ====", e);
            throw e;
        }
        return state>0;
    }

    @Override
    public PageInfo<Test> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        Page<Test> pages = testMapper.findByPage();
        PageInfo<Test> pageInfo = new PageInfo<>(pages);
        return pageInfo;
    }

}
