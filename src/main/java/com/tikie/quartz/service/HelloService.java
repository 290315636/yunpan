/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月17日
 * 修改历史：
 * 		1、[2018年7月17日]创建文件 by zhaocs
 */
package com.tikie.quartz.service;

import java.util.concurrent.TimeUnit;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.tikie.quartz.job.HelloJobOne;

/**
 * @author zhaocs
 *
 */
public class HelloService {
    public void haveProperties() throws SchedulerException, InterruptedException{
        /*
         *在 Quartz 中， scheduler 由 scheduler 工厂创建：
         * DirectSchedulerFactory 或者 StdSchedulerFactory。
         *第二种工厂 StdSchedulerFactory 使用较多，
         *因为 DirectSchedulerFactory 使用起来不够方便，需要作许多详细的手工编码设置。
         */
        // 获取Scheduler实例
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        System.out.println("scheduler.start");
        //具体任务.
        JobDetail jobDetail = JobBuilder.newJob(HelloJobOne.class).withIdentity("job1","group1").build();
        //触发时间点. (每5秒执行1次.)
        //SimpleScheduleBuilder是简单调用触发器，它只能指定触发的间隔时间和执行次数
        //CronScheduleBuilder是类似于Linux Cron的触发器，它通过一个称为CronExpression的规则来指定触发规则，通常是每次触发的具体时间
        //CalendarIntervalScheduleBuilder是对CronScheduleBuilder的补充，它能指定每隔一段时间触发一次
        SimpleScheduleBuilder simpleScheduleBuilder = SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(5).repeatForever();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1","group1").startNow().withSchedule(simpleScheduleBuilder).build();
        // 交由Scheduler安排触发
        scheduler.scheduleJob(jobDetail,trigger);
        //睡眠20秒.
        TimeUnit.SECONDS.sleep(20);
        scheduler.shutdown();//关闭定时任务调度器.
        System.out.println("scheduler.shutdown");
    }
}
