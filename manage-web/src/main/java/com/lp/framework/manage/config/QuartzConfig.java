package com.lp.framework.manage.config;

import com.lp.framework.manage.quartz.listener.SchedulerJobListener;
import org.quartz.ListenerManager;
import org.quartz.Scheduler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig implements InitializingBean {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void afterPropertiesSet() throws Exception {
        ListenerManager listenerManager = scheduler.getListenerManager();
        listenerManager.addJobListener(new SchedulerJobListener());
    }

}
