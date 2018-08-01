package com.tikie.file.service.impl;

import com.tikie.file.dao.FileShareMapper;
import com.tikie.file.model.FileShare;
import com.tikie.file.service.FileShareService;
import com.tikie.util.StringRandomUtil;
import com.tikie.util.UUIDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zhangshitai
 * @date 2018-07-31
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class FileShareServiceImpl implements FileShareService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FileShareMapper fileShareMapper;


    @Override
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    public Boolean insertSelective(String fileTreeIds, String code) {
        int state = 0;
        FileShare fileShare = new FileShare();
        try{
            fileShare.setId(UUIDUtil.getUUID());
            fileShare.setTreeIds(fileTreeIds);
            fileShare.setCode(code);
            state =  fileShareMapper.insertSelective(fileShare);
            logger.info("insertSelective@exec:{}",state);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("insertSelective@err:{}",e);
        }
        return state > 0;
    }

    @Override
    public FileShare selectByCode(String code) {
        FileShare fileShare = new FileShare();
        try {
            fileShare.setCode(code);
            fileShare = fileShareMapper.selectBySelective(fileShare);
            String[] fileIds = fileShare.getTreeIds().split(",");
            for (int i = 0;i<fileIds.length;i++){
                String id = fileIds[i];

            }
            logger.info("==== selectFileTreeByName@exec:{} ====", fileShare);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("==== selectFileTreeByName@err:{} ====", e);
        }
        return fileShare;
    }

    @Override
    public String showCode(String fileTreeIds) {
        FileShare fileShare = new FileShare();
        String code = null;
        try{
            code = StringRandomUtil.getStringRandom(24);
            fileShare.setId(UUIDUtil.getUUID());
            fileShare.setTreeIds(fileTreeIds);
            fileShare.setCode(code);
            fileShareMapper.insertSelective(fileShare);
            logger.info("insertSelective@exec:{}",code);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("insertSelective@err:{}",e);
        }
        return code;
    }
}
