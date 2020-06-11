package com.lp.framework.manage.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class SchedulerJobListener extends JobListenerSupport {

    private static final String name = "jobListener";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("begin===========");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("cancel===========");;
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("end===========");;
    }
}
