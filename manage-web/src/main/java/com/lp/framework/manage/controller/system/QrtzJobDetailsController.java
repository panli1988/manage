package com.lp.framework.manage.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lp.framework.manage.model.QrtzJobDetails;
import com.lp.framework.manage.model.QrtzTriggers;
import com.lp.framework.manage.quartz.manager.SchedulerManager;
import com.lp.framework.manage.service.QrtzJobDetailsService;
import com.lp.framework.manage.utils.CommonUtils;
import com.lp.framework.manage.utils.JsonResult;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

@RequestMapping("/qrtzJobDetails")
@Controller
public class QrtzJobDetailsController {

    @Autowired
    private QrtzJobDetailsService qrtzJobDetailsService;

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/index")
    public String index(){
        return "system/job/qrtzJobDetails";
    }

    @GetMapping("/queryPage")
    @ResponseBody
    public JsonResult queryPage(ServletRequest request){
        Map<String, Object> params = CommonUtils.getParametersMap(request);
        JsonResult jsonResult = new JsonResult();
        try {
            int pageNum = Integer.parseInt((String) params.get("pageNum"));
            int pageSize = Integer.parseInt((String) params.get("pageSize"));
            PageHelper.startPage(pageNum,pageSize);
            List<QrtzJobDetails> jobDetailsList = qrtzJobDetailsService.selectByPage(params);
            PageInfo<QrtzJobDetails> pageInfo = new PageInfo<QrtzJobDetails>(jobDetailsList);
            List<QrtzJobDetails> list = pageInfo.getList();
            for(QrtzJobDetails qrtzJobDetails:list){
                JobKey jobKey = SchedulerManager.getJobKey(qrtzJobDetails.getJobName());
                List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
                if(ObjectUtils.isEmpty(triggers)){
                    qrtzJobDetails.setState("NONE");
                }else{
                    Trigger trigger = triggers.get(0);
                    TriggerKey triggerKey = trigger.getKey();
                    Trigger.TriggerState triggerState = scheduler.getTriggerState(triggerKey);
                    String stateName = triggerState.name();
                    qrtzJobDetails.setState(stateName);
                }
            }
            jsonResult = new JsonResult(pageInfo);
        } catch (NumberFormatException | SchedulerException e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("查询失败");
        }
        return jsonResult;
    }

    @PostMapping("/insert")
    @ResponseBody
    public JsonResult insert(ServletRequest request,@RequestBody QrtzJobDetails qrtzJobDetails){
        JsonResult jsonResult = new JsonResult();
        try {
            JobDetail jobDetail = SchedulerManager.createJobDetail(qrtzJobDetails);
            scheduler.addJob(jobDetail,false);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(ServletRequest request,@RequestBody QrtzJobDetails qrtzJobDetails){
        JsonResult jsonResult = new JsonResult();
        try {
            JobKey jobKey = SchedulerManager.getJobKey(qrtzJobDetails.getJobName());
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            JobDetail newJobDetail = jobDetail.getJobBuilder().withDescription(qrtzJobDetails.getDescription()).build();
            scheduler.addJob(newJobDetail,true);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @GetMapping("/queryQrtzTriggers")
    @ResponseBody
    public JsonResult queryQrtzTriggers(ServletRequest request){
        Map<String, Object> params = CommonUtils.getParametersMap(request);
        JsonResult jsonResult = new JsonResult();
        try {
            String jobName = MapUtils.getString(params, "jobName");
            JobKey jobKey = SchedulerManager.getJobKey(jobName);
            List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
            List<QrtzTriggers> list = new ArrayList<>();
            for(Trigger cronTrigger: triggers){
                QrtzTriggers qrtzTriggers = new QrtzTriggers((CronTrigger) cronTrigger);
                list.add(qrtzTriggers);
            }
            jsonResult.setSuccess(true);
            jsonResult.setList(list);
        } catch (SchedulerException e) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("查询失败");
        }
        return jsonResult;
    }

    @PostMapping("/insertTrigger")
    @ResponseBody
    public JsonResult insertTrigger(ServletRequest request, @RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            String triggerName = MapUtils.getString(params, "triggerName");
            String jobName = MapUtils.getString(params, "jobName");
            String description = MapUtils.getString(params, "description");
            String cronExpress = MapUtils.getString(params, "cronExpress");
            String startTime = MapUtils.getString(params,"startTime");
            String endTime = MapUtils.getString(params,"endTime");
            DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = sf.parse(startTime);
            Date end = sf.parse(endTime);
            CronTrigger cronTrigger = SchedulerManager.createTrigger(triggerName, jobName, description, cronExpress, start, end);
            scheduler.scheduleJob(cronTrigger);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/pauseJob")
    @ResponseBody
    public JsonResult pauseJob(ServletRequest request, @RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            String jobName = MapUtils.getString(params, "jobName");
            JobKey jobKey = SchedulerManager.getJobKey(jobName);
            scheduler.pauseJob(jobKey);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/resumeJob")
    @ResponseBody
    public JsonResult resumeJob(ServletRequest request, @RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            String jobName = MapUtils.getString(params, "jobName");
            JobKey jobKey = SchedulerManager.getJobKey(jobName);
            scheduler.resumeJob(jobKey);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/deleteJob")
    @ResponseBody
    public JsonResult deleteJob(ServletRequest request, @RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            String jobName = MapUtils.getString(params, "jobName");
            JobKey jobKey = SchedulerManager.getJobKey(jobName);
            scheduler.pauseJob(jobKey);
            scheduler.deleteJob(jobKey);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/triggerJob")
    @ResponseBody
    public JsonResult triggerJob(ServletRequest request, @RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            String jobName = MapUtils.getString(params, "jobName");
            JobKey jobKey = SchedulerManager.getJobKey(jobName);
            scheduler.triggerJob(jobKey);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

    @PostMapping("/deleteTrigger")
    @ResponseBody
    public JsonResult deleteTrigger(ServletRequest request, @RequestBody Map<String,Object> params){
        JsonResult jsonResult = new JsonResult();
        try {
            String triggerName = MapUtils.getString(params, "triggerName");
            TriggerKey triggerKey = SchedulerManager.getTriggerKey(triggerName);
            scheduler.unscheduleJob(triggerKey);
            jsonResult.setSuccess(true);
            jsonResult.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setSuccess(false);
            jsonResult.setMsg("操作失败");
        }
        return jsonResult;
    }

}
