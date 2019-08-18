package com.project.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.quartz.Trigger;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.project.job.TestJob;

@Configuration
public class QuartzConfig {

	
	/**
     * 方法调用任务明细工厂Bean
     */
    @Bean(name = "JobBean")
    public MethodInvokingJobDetailFactoryBean myFirstExerciseJobBean(TestJob testJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        jobDetail.setConcurrent(false); // 是否并发
        jobDetail.setName("general-myJob"); // 任务的名字
        jobDetail.setGroup("general"); // 任务的分组
        jobDetail.setTargetObject(testJob); // 被执行的对象
        jobDetail.setTargetMethod("startJob"); // 被执行的方法
        return jobDetail;
    }
    /**
     * 表达式触发器工厂Bean
     */
    @Bean(name = "JobTrigger")
    public CronTriggerFactoryBean myFirstExerciseJobTrigger(@Qualifier("JobBean") MethodInvokingJobDetailFactoryBean JobBean) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(JobBean.getObject());
        tigger.setCronExpression("0/10 * * * * ?"); // 什么是否触发，Spring Scheduler Cron表达式
        tigger.setName("general-myTrigger");
        return tigger;
    }
    
    @Bean(name = "schedulerFactory")
    public SchedulerFactoryBean schedulerFactory(@Qualifier("JobTrigger") Trigger JobTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 覆盖已存在的任务
        bean.setOverwriteExistingJobs(true);
        // 延时启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        bean.setStartupDelay(15);
        // 注册触发器
        bean.setTriggers(JobTrigger);
        //设置自定义配置文件
        bean.setConfigLocation(new ClassPathResource("quartz.properties"));
        return bean;
    }
    
}
