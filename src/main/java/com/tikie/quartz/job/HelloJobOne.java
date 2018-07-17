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

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhaocs
 * @Description 任务类.
 */
public class HelloJobOne implements Job {
    Logger logger = LoggerFactory.getLogger(getClass());
    
    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        logger.debug("一号任务{}", new Date());
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        for (@SuppressWarnings("rawtypes") Map.Entry entry : jobDataMap.entrySet()){
            logger.debug("一号任务:key---: " + entry.getKey() + ";value---: " + entry.getValue());
        }
    }

}
