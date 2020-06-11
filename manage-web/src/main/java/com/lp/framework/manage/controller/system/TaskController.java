package com.lp.framework.manage.controller.system;

import com.lp.framework.manage.quartz.manager.SchedulerManager;
import com.lp.framework.manage.utils.JsonResult;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/taskIndex")
    public String index(){
        return "system/task/taskIndex";
    }

    /**
     * 新增job
     * @return
     */
    @GetMapping("/addJob")
    @ResponseBody
    public JsonResult addJob(){
        JsonResult jsonResult = new JsonResult();
        try {
            JobDetail jobDetail = SchedulerManager.createJobDetail("testJob", "com.lp.framework.manage.job.TestJob","测试");
            CronTrigger cronTrigger = SchedulerManager.createTrigger("testTrigger", jobDetail, "0/5 * * * * ?");
            Date date = scheduler.scheduleJob(jobDetail,cronTrigger);
            System.out.println("date:"+date);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (ClassNotFoundException | SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    /**
     * 新增job
     * @return
     */
    @GetMapping("/addJob2")
    @ResponseBody
    public JsonResult addJob2(){
        JsonResult jsonResult = new JsonResult();
        try {
            JobDetail jobDetail = SchedulerManager.createJobDetail("testJob", "com.lp.framework.manage.job.TestJob","测试");
            CronTrigger cronTrigger = SchedulerManager.createTrigger("testTrigger", jobDetail, "0/5 * * * * ?");
            scheduler.addJob(jobDetail,false);
            scheduler.scheduleJob(cronTrigger);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (ClassNotFoundException | SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    /**
     * 删除JOB
     * @return
     */
    @GetMapping("/deleteJob")
    @ResponseBody
    public JsonResult deleteJob(){
        JsonResult jsonResult = new JsonResult();
        try {
            SchedulerManager.pauseJob(scheduler,"testJob");
            SchedulerManager.deleteJob(scheduler,"testJob");
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    /**
     * 暂停job
     * @return
     */
    @GetMapping("/pauseJob")
    @ResponseBody
    public JsonResult pauseJob(){
        JsonResult jsonResult = new JsonResult();
        try {
            SchedulerManager.pauseJob(scheduler,"testJob");
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    /**
     * 重新启动job
     * @return
     */
    @GetMapping("/resumeJob")
    @ResponseBody
    public JsonResult resumeJob(){
        JsonResult jsonResult = new JsonResult();
        try {
            SchedulerManager.resumeJob(scheduler,"testJob");
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    /**
     * 执行job
     * @return
     */
    @GetMapping("/executeJob")
    @ResponseBody
    public JsonResult executeJob(){
        JsonResult jsonResult = new JsonResult();
        try {
            SchedulerManager.triggerJob(scheduler,"testJob");
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() throws SchedulerException {
        Trigger.TriggerState triggerState = scheduler.getTriggerState(SchedulerManager.getTriggerKey("testTrigger"));
        System.out.println(triggerState);
        JobDetail jobDetail = scheduler.getJobDetail(SchedulerManager.getJobKey("testJob"));
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        System.out.println(jobDataMap);
        return new Date().toString();
    }
}
