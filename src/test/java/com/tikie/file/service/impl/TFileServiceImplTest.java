package com.tikie.file.service.impl;

import com.tikie.file.model.TFileDTO;
import com.tikie.file.service.TFileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TFileServiceImplTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFileService tFileService;


    @Test
    public void deleteByPrimaryKey() {
        TFileDTO tFile = new TFileDTO();
        tFile.setId("1");
        tFileService.deleteByPrimaryKey(tFile.getId());
    }

    @Test
    public void insert() {
    }

    @Test
    public void insertSelective() {
    }

    @Test
    public void selectByPrimaryKey() {
    }

    @Test
    public void updateByPrimaryKeySelective() {
    }

    @Test
    public void updateByPrimaryKey() {
    }
}