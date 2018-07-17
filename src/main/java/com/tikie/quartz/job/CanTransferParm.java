/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月17日
 * 修改历史：
 * 		1、[2018年7月17日]创建文件 by zhaocs
 */
package com.tikie.quartz.job;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.tikie.file.service.TestService;
import com.tikie.util.UUIDUtil;

/**
 * @author zhaocs
 * 
 */
public class CanTransferParm extends QuartzJobBean {
    Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private TestService testService;
    
    /* (non-Javadoc)
     * @see org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org.quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobKey key = context.getJobDetail().getKey();
        logger.debug("key :{} ", key);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        for (@SuppressWarnings("rawtypes") Map.Entry entry : jobDataMap.entrySet()){
            logger.debug("key---: " + entry.getKey() + ";value---: " + entry.getValue());
        }
        
        
        // 模拟更新数据库
        com.tikie.file.model.Test record = new com.tikie.file.model.Test();
        record.setId(UUIDUtil.getUUID());
        record.setMsg("hello tikie 2 db");
        record.setNote("模拟更新数据库");
        record.setCtime(new Date());
        logger.info("==== {} ====", testService.insertSelective(record));
    }

}
