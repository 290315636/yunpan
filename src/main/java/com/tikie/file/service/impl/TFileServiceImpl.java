package com.tikie.file.service.impl;

import com.tikie.file.mapper.TFileMapper;
import com.tikie.file.model.TFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhangshitai
 * @date 2018-07-20
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class TFileServiceImpl implements TFileMapper{

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TFileMapper tFileMapper;

    @Override
    public int deleteByPrimaryKey(String id) {
        return 0;
    }

    @Override
    public int insert(TFile record) {
        return 0;
    }

    @Override
    public int insertSelective(TFile record) {
        return 0;
    }

    @Override
    public TFile selectByPrimaryKey(String id) {
        TFile tFile = null;
        try {
            tFile = tFileMapper.selectByPrimaryKey(id);
            logger.info("==== selectByPrimaryKey@exec:{} ====", tFile);
        }catch (Exception e){
            logger.error("==== selectByPrimaryKey@err:{} ====", e);
        }
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(TFile record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(TFile record) {
        return 0;
    }
}
