package com.scheduler.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;

import com.core.job.JobRequest;
import com.scheduler.engine.SchedulerEngine;
import com.scheduler.engine.SchedulerHelper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StartUpJob extends AbstractJob {
	
	@Autowired private SchedulerHelper schdlHelper;
	@Autowired private SchedulerEngine schdlEngine;

	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		List<JobRequest> allJobRequests = schdlHelper.loadAllPredefinedJobs();
		for (JobRequest jobReq : allJobRequests) {
			try {
				schdlEngine.schedule(jobReq);
			} catch (SchedulerException e) {
				e.printStackTrace();
				log.error(String.format("Job start failed. \n%s", jobReq), e);
				continue;
			}
		}
	}

}
