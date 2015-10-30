package com.scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.core.job.JobSpec;
import com.core.job.Task;
import com.core.job.TaskResult;

public abstract class AbstractTaskRunnerJob extends AbstractJob {
	
	public static final String KEY_JOB_SPEC = "jobSpec";

	@Override
	protected void doExecute(JobExecutionContext context) throws JobExecutionException {
		JobSpec jobSpec = (JobSpec)context.getJobDetail().getJobDataMap().get(KEY_JOB_SPEC);
		Task task = new Task();
		task.setJobSpec(jobSpec);
		
		run(task);
	}
	
	abstract TaskResult run(Task task);
}
