package com.lp.framework.manage.model;

import org.quartz.CronTrigger;

import java.io.Serializable;
import java.util.Date;

/**
 * qrtz_triggers
 * @author 
 */
public class QrtzTriggers extends QrtzTriggersKey implements Serializable {
    private String jobName;

    private String jobGroup;

    private String description;

    private Date nextFireTime;

    private Date prevFireTime;

    private Integer priority;

    private String triggerState;

    private String triggerType;

    private Date startTime;

    private Date endTime;

    private String calendarName;

    private Short misfireInstr;

    private String cronExpression;

    private static final long serialVersionUID = 1L;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getNextFireTime() {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime) {
        this.nextFireTime = nextFireTime;
    }

    public Date getPrevFireTime() {
        return prevFireTime;
    }

    public void setPrevFireTime(Date prevFireTime) {
        this.prevFireTime = prevFireTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getTriggerState() {
        return triggerState;
    }

    public void setTriggerState(String triggerState) {
        this.triggerState = triggerState;
    }

    public String getTriggerType() {
        return triggerType;
    }

    public void setTriggerType(String triggerType) {
        this.triggerType = triggerType;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public Short getMisfireInstr() {
        return misfireInstr;
    }

    public void setMisfireInstr(Short misfireInstr) {
        this.misfireInstr = misfireInstr;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public QrtzTriggers(CronTrigger cronTrigger) {
        this.jobName = cronTrigger.getJobKey().getName();
        this.setTriggerName(cronTrigger.getKey().getName());
        this.description = cronTrigger.getDescription();
        this.startTime = cronTrigger.getStartTime();
        this.endTime = cronTrigger.getEndTime();
        this.nextFireTime = cronTrigger.getPreviousFireTime();
        this.prevFireTime = cronTrigger.getNextFireTime();
        this.cronExpression = cronTrigger.getCronExpression();
    }
}