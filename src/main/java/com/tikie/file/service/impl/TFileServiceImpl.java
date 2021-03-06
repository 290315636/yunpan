package com.tikie.file.service.impl;

import com.tikie.file.dao.TFileMapper;
import com.tikie.file.model.TFile;
import com.tikie.file.service.TFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class TFileServiceImpl implements TFileService{

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TFileMapper tFileMapper;


    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean deleteByPrimaryKey(String id) {
        int state = 0;
        try{
            state =  tFileMapper.deleteByPrimaryKey(id);
            logger.info("deleteByPrimaryKey@exec:{}",state);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("deleteByPrimaryKey@err:{}",e);
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean insert(TFile record) {
        int state = 0;
        try{
            state =  tFileMapper.insert(record);
            logger.info("insert@exec:{}",state);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("insert@err:{}",e);
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public Boolean insertSelective(TFile record) { 
        int state = 0;
        try{
            state =  tFileMapper.insertSelective(record);
            logger.info("insertSelective@exec:{}",state);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("insertSelective@err:{}",e);
        }
        return state > 0;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public TFile selectByPrimaryKey(String id) {
    	TFile tFile = new TFile();
        try {
            tFile.setId(id);
            tFile = tFileMapper.selectByPrimaryKey(id);
            logger.info("==== selectByPrimaryKey@exec:{} ====", tFile);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== selectByPrimaryKey@err:{} ====", e);
        }
        return tFile;
    }

    @Override
    public Boolean updateByPrimaryKeySelective(TFile record) {
        int state = 0;
        try{
            state =  tFileMapper.updateByPrimaryKeySelective(record);
            logger.info("==== updateByPrimaryKeySelective@exec:{} ====", state);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== updateByPrimaryKeySelective@err:{} ====", e);
        }
        return state > 0;
    }

    @Override
    public Boolean updateByPrimaryKey(TFile record) {
        int state = 0;
        try{
            state =  tFileMapper.updateByPrimaryKey(record);
            logger.info("==== updateByPrimaryKey@exec:{} ====", state);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== updateByPrimaryKey@err:{} ====", e);
        }
        return state > 0;
    }

    /**
     *  根据文件的md5去数据库查找记录：存在返回true
     */
    @Override
    public Boolean checkMd5FromDB(String md5) {
        TFile record = new TFile();
        record.setMd5(md5);
        List<TFile> list = Collections.emptyList();
        try{
            list = tFileMapper.selectSelective(record);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== checkMd5FromDB@err:{} ====", e);
        }
        return !list.isEmpty();
    }

    @Override
    public String selectIdByMd5(String md5) {
        String id = null;
        try {
            id = tFileMapper.selectIdByMd5(md5);
            logger.info("==== selectIdByMd5@exec:{} ====", id);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== selectIdByMd5@err:{} ====", e);
        }
        return id;
    }


}
