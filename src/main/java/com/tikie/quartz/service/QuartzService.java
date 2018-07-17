/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月17日
 * 修改历史：
 * 		1、[2018年7月17日]创建文件 by zhaocs
 */
package com.tikie.quartz.service;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhaocs
 *
 */
@Service
public class QuartzService {
    @Autowired
    private Scheduler scheduler;

    public void startJob(String time,String jobName,String group,Class job){
        try {
            // 创建jobDetail实例，绑定Job实现类
            // 指明job的名称，所在组的名称，以及绑定job类
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, group).build();//设置Job的名字和组
            //corn表达式  每2秒执行一次
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time/*"0/2 * * * * ?"*/);
            //设置定时任务的时间触发规则
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName,group) .withSchedule(scheduleBuilder).build();
            System.out.println(scheduler.getSchedulerName());
            // 把作业和触发器注册到任务调度中, 启动调度
            scheduler.scheduleJob(jobDetail,cronTrigger);
        /*
        // 启动调度
         scheduler.start();
         Thread.sleep(30000);
        // 停止调度
        scheduler.shutdown();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startJob2(String time,String jobName,String group,Class job){
        try {
            JobDetail jobDetail = JobBuilder.newJob(job).withIdentity(jobName, group).build();//设置Job的名字和组
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(time/*"0/2 * * * * ?"*/);
            CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName,group) .withSchedule(scheduleBuilder).build();
            System.out.println(scheduler.getSchedulerName());
            scheduler.scheduleJob(jobDetail,cronTrigger);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /****
     * 暂停一个任务
     * @param triggerName
     * @param triggerGroupName
     */
    public void pauseJob(String triggerName,String triggerGroupName){
        try {
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail==null){
                return;
            }
            System.out.println("开始暂停一个定时器");
            scheduler.pauseJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /****
     * 暂停重启一个定时器任务
     * @param triggerName
     * @param triggerGroupName
     */
    public void resumeJob(String triggerName,String triggerGroupName){
        try {
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail==null){
                return;
            }
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }



    /****
     * 删除一个定时器任务，删除了，重启就没什么用了
     * @param triggerName
     * @param triggerGroupName
     */
    public void deleteJob(String triggerName,String triggerGroupName){
        try {
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail==null){
                return;
            }
            scheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }




    /***
     * 根据出发规则匹配任务，立即执行定时任务，暂停的时候可以用
     */
    public void doJob(String triggerName,String triggerGroupName){
        try {
            JobKey jobKey = JobKey.jobKey(triggerName, triggerGroupName);
            scheduler.triggerJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    /***
     * 开启定时器，这时才可以开始所有的任务，默认是开启的
     */
    public void startAllJob(){
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭定时器，则所有任务不能执行和创建
     */
    public void shutdown(){
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
