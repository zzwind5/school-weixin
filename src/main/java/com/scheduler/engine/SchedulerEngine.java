package com.scheduler.engine;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.job.JobRequest;
import com.scheduler.job.AbstractTaskRunnerJob;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class SchedulerEngine {

	private Scheduler scheduler;
	
	@Autowired private SchedulerHelper schdlHelper;
	
	@PostConstruct
	public void initialize() throws SchedulerException {
		log.info("Starting Quartz scheduler...");
		scheduler = new StdSchedulerFactory().getScheduler();
		scheduler.start();
		
		JobDetail jobDetail = schdlHelper.newStartUpJobDetail();
		Trigger trigger = schdlHelper.newStartupJobTrigger(jobDetail);
		scheduler.scheduleJob(jobDetail, trigger);
	}
	
	public void schedule(final JobRequest jobRequest) throws SchedulerException {
		JobDetail jobDetail = schdlHelper.newJobDetail(jobRequest.getSpec());
		Trigger trigger = schdlHelper.newJobTrigger(jobRequest.getSpec(), jobRequest.getTrigger());
		jobDetail.getJobDataMap().put(AbstractTaskRunnerJob.KEY_JOB_SPEC, jobRequest.getSpec());
		scheduler.scheduleJob(jobDetail, trigger);
	}
}
