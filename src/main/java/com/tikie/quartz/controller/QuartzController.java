/**
 * 
 * 项目名称：tikie-yunpan
 * 创建日期：2018年7月17日
 * 修改历史：
 * 		1、[2018年7月17日]创建文件 by zhaocs
 */
package com.tikie.quartz.controller;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tikie.quartz.job.CanTransferParm;
import com.tikie.quartz.job.HelloJobOne;
import com.tikie.quartz.job.HelloJobTwo;
import com.tikie.quartz.service.QuartzService;

/**
 * @author zhaocs
 *
 */
@RestController
@RequestMapping("/")
public class QuartzController {

    @Autowired
    private QuartzService quartzService;


    @GetMapping(value = "quartzStart", produces = { "application/json;charset=UTF-8" })
    public String startNNoQuartz(){
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("param1", "tikie");
        jobDataMap.put("param2", "charlie");
        jobDataMap.put("location", "china");
        quartzService.startJob("0/1 * * * * ? ","job1","gropu1",jobDataMap, HelloJobOne.class);
//        quartzService.startJob("0/2 * * * * ? ","job2","gropu2", HelloJobTwo.class);
        
        quartzService.startJob4("0/2 * * * * ? ","job3","gropu3",jobDataMap, CanTransferParm.class);
        return "定时器任务开始执行，请注意观察控制台";
    }

    @GetMapping(value = "pauseJob", produces = { "application/json;charset=UTF-8" })
    public String pauseJob(){
        quartzService.pauseJob("job1","gropu1");
        return "暂停一个定时器任务，请注意观察控制台";
    }


    @GetMapping(value = "resumeJob", produces = { "application/json;charset=UTF-8" }) //shutdown关闭了，或者删除了就不能重启了
    public String resumeJob(){
        quartzService.resumeJob("job1","gropu1");
        return "暂停重启一个定时器任务，请注意观察控制台";
    }

    @GetMapping(value = "deleteJob", produces = { "application/json;charset=UTF-8" })
    public String deleteJob(){
        quartzService.deleteJob("job1","gropu1");
        return "删除一个定时器任务，请注意观察控制台，删除了，重启就没什么用了";
    }



    @GetMapping(value = "doJob", produces = { "application/json;charset=UTF-8" })
    public String doJob(){
        quartzService.doJob("job1","gropu1");
        return "根据出发规则匹配任务，立即执行定时任务，暂停的时候可以用";
    }

    @GetMapping(value = "startAllJob", produces = { "application/json;charset=UTF-8" })
    public String startAllJob(){
        quartzService.startAllJob();
        return "开启定时器，这时才可以开始所有的任务，默认是开启的";
    }

    @GetMapping(value = "shutdown", produces = { "application/json;charset=UTF-8" })
    public String shutdown(){
        quartzService.shutdown();
        return "关闭定时器，则所有任务不能执行和创建";
    }

}
