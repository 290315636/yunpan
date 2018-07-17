/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月17日
 * 修改历史：
 * 		1、[2018年7月17日]创建文件 by zhaocs
 */
package com.tikie.quartz.job;

import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author zhaocs
 * @Description 任务类.
 */
public class HelloJobTwo implements Job {

    /* (non-Javadoc)
     * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
     */
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        System.out.println("二号任务" + new Date());
    }

}
