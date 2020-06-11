package com.lp.framework.manage.quartz.manager;

import com.lp.framework.manage.model.QrtzJobDetails;
import org.quartz.*;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 调度管理类
 */
public class SchedulerManager {

    private static final Logger log = LoggerFactory.getLogger(SchedulerManager.class);

    /**
     * 创建JobDetail
     * @param jobName job名称
     * @param clsName job类
     * @return
     */
    public static JobDetail createJobDetail(String jobName,String clsName,String description) throws ClassNotFoundException {
        JobDetail jobDetail = null;
        jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(clsName)).withIdentity(jobName).withDescription(description).storeDurably().build();
        return jobDetail;
    }

    public static JobDetail createJobDetail(QrtzJobDetails qrtzJobDetails) throws ClassNotFoundException {
        JobDetail jobDetail = null;
        jobDetail = JobBuilder.newJob((Class<? extends Job>) Class.forName(qrtzJobDetails.getJobClassName())).withIdentity(qrtzJobDetails.getJobName()).withDescription(qrtzJobDetails.getDescription()).storeDurably().build();
        return jobDetail;
    }

    /**
     * 创建触发器
     * @param triggerName
     * @param jobDetail
     * @param cron
     * @return
     */
    public static CronTrigger createTrigger(String triggerName, JobDetail jobDetail, String cron){
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName).withSchedule(CronScheduleBuilder.cronSchedule(cron)).forJob(jobDetail).build();
        return cronTrigger;
    }

    /**
     * 创建触发器
     * @param triggerName
     * @param jobName
     * @param cron
     * @return
     */
    public static CronTrigger createTrigger(String triggerName, String jobName, String cron){
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(triggerName).forJob(jobName).withSchedule(CronScheduleBuilder.cronSchedule(cron)).build();
        return cronTrigger;
    }

    /**
     * 创建触发器（含开始时间和结束时间）
     * @param triggerName
     * @param jobDetail
     * @param cron
     * @param startTime
     * @param endTime
     * @return
     */
    public static CronTrigger createTrigger(String triggerName,String description, JobDetail jobDetail, String cron, Date startTime,Date endTime){
        CronTrigger cronTrigger = (CronTriggerImpl) TriggerBuilder.newTrigger().withIdentity(triggerName).withDescription(description).forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .startAt(startTime).endAt(endTime).build();
        return cronTrigger;
    }

    /**
     * 创建触发器（含开始时间和结束时间）
     * @param triggerName
     * @param jobName
     * @param description
     * @param cron
     * @param startTime
     * @param endTime
     * @return
     */
    public static CronTrigger createTrigger(String triggerName, String jobName,String description, String cron, Date startTime,Date endTime){
        CronTrigger cronTrigger = (CronTriggerImpl) TriggerBuilder.newTrigger().withIdentity(triggerName).withDescription(description).forJob(jobName).withSchedule(CronScheduleBuilder.cronSchedule(cron))
                .startAt(startTime).endAt(endTime).build();
        return cronTrigger;
    }

    /**
     * 添加job
     * @param scheduler
     * @param jobDetail
     * @throws SchedulerException
     */
    public static void addJob(Scheduler scheduler,JobDetail jobDetail) throws SchedulerException {
        scheduler.addJob(jobDetail,true);
    }

    /**
     * 增加调度任务
     * @param scheduler
     * @param trigger
     * @return
     * @throws SchedulerException
     */
    public static Date scheduledJob(Scheduler scheduler,Trigger trigger) throws SchedulerException {
        Date date = scheduler.scheduleJob(trigger);
        return date;
    }

    /**
     * 获取JobKey
     * @param jobName
     * @return
     */
    public static JobKey getJobKey(String jobName){
        return JobKey.jobKey(jobName);
    }

    /**
     * 获取TriggerKey
     * @param triggerName
     * @return
     */
    public static TriggerKey getTriggerKey(String triggerName){
        return TriggerKey.triggerKey(triggerName);
    }

    /**
     * 暂停JOB
     * @param scheduler
     * @param jobName
     * @throws SchedulerException
     */
    public static void pauseJob(Scheduler scheduler, String jobName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName);
        scheduler.pauseJob(jobKey);
    }

    /**
     * 重启JOB
     * @param scheduler
     * @param jobName
     * @throws SchedulerException
     */
    public static void resumeJob(Scheduler scheduler, String jobName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName);
        scheduler.resumeJob(jobKey);
    }

    /**
     * 删除JOB
     * @param scheduler
     * @param jobName
     * @throws SchedulerException
     */
    public static void deleteJob(Scheduler scheduler, String jobName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName);
        scheduler.deleteJob(jobKey);
    }

    /**
     * 触发job
     * @param scheduler
     * @param jobName
     * @throws SchedulerException
     */
    public static void triggerJob(Scheduler scheduler, String jobName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName);
        scheduler.triggerJob(jobKey);
    }

    /**
     * 获取jobDetail
     * @param scheduler
     * @param jobName
     * @return
     * @throws SchedulerException
     */
    public static JobDetail getJobDetail(Scheduler scheduler, String jobName) throws SchedulerException {
        JobKey jobKey = getJobKey(jobName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        return jobDetail;
    }



}
