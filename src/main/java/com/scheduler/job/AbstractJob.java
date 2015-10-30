package com.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.core.util.ApplicationContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractJob implements Job {

	@Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        ApplicationContextHolder.autowire(this);
        try {
            doExecute(context);
        } catch (JobExecutionException je) {
            throw je;
        } catch (Exception e) {
        	e.printStackTrace();
            log.error("Unexpected exception occurs during job execution.", e);
        }
    }

    protected abstract void doExecute(final JobExecutionContext context) throws JobExecutionException;
}
