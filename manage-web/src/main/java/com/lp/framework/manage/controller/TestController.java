package com.lp.framework.manage.controller;

import com.lp.framework.manage.job.TestJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class TestController {

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        return "success";
    }

    @GetMapping("/addJob")
    @ResponseBody
    public String testAddJob() throws SchedulerException {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("testJob").storeDurably().build();
        boolean disallowed = jobDetail.isConcurrentExectionDisallowed();
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("testJobTrigger").withSchedule(CronScheduleBuilder.cronSchedule("0,5,10 45,48 * * * ?")).forJob(jobDetail).build();
        Trigger trigger2 = TriggerBuilder.newTrigger().withIdentity("testJobTrigger2").withSchedule(CronScheduleBuilder.cronSchedule("4,6,8 50 * * * ?")).forJob(jobDetail).build();
        Set<Trigger> set = new HashSet<>();
        set.add(trigger);
        set.add(trigger2);
        scheduler.scheduleJob(jobDetail,set,true);
        return "success";
    }

    @GetMapping("/table")
    public String table(){
        return "test/table";
    }
}
