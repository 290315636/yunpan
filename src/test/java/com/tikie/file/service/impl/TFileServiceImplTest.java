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
        tFile.setId("83c16549ad364386ab7712cb3ae9e750");
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
        TFile tFile = new TFile();
        tFile.setId(UUIDUtil.getUUID());
        tFile.setName("test_1");
        tFile.setPath("c:/");
        tFile.setSize(123L);
        tFileService.insert(tFile);
    }

    @Test
    public void selectByPrimaryKey() {
        tFileService.selectByPrimaryKey("37b90e5c1b5d44aa9e7926d1c86c741d");
    }

    @Test
    public void updateByPrimaryKeySelective() {
        TFile tFile = new TFile();
        tFile.setId("d21bd37ca6e54b42baa3618828f45cc3");
        tFile.setPath("d:/");
        tFileService.updateByPrimaryKeySelective(tFile);
    }

    @Test
    public void updateByPrimaryKey() {
        TFile tFile = new TFile();
        tFile.setId("37b90e5c1b5d44aa9e7926d1c86c741d");
        tFile.setPath("f:/");
        tFileService.updateByPrimaryKey(tFile);
    }
}