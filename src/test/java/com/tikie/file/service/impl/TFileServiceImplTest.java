package com.tikie.file.service.impl;

import com.tikie.file.model.TFile;
import com.tikie.file.service.TFileService;
import com.tikie.util.UUIDUtil;

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
        TFile tFile = new TFile();
        tFile.setId("1");
        tFileService.deleteByPrimaryKey(tFile.getId());
    }

    @Test
    public void insert() {
        TFile tFile = new TFile();
        tFile.setId(UUIDUtil.getUUID());
        tFile.setName("test_1");
        tFile.setPath("c:/");
        tFile.setSize(123L);
        tFileService.insert(tFile);
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