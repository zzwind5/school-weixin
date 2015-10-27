package com.core.task.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.core.task.Task;
import com.core.task.TaskResult;
import com.google.common.base.Stopwatch;

public abstract class AbstractorTaskRunner implements TaskRunner {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private static final int DEFAULT_CORE_POOL_SIZE 	= 3;
	private static final int DEFAULT_MAX_POOL_SIZE 		= 10;

	@Override
	public String getRunnerCode() {
		return this.getClass().getSimpleName();
	}

	@Override
	public TaskResult run(Task task) {
		logger.info("Run task {}...", task);
		Stopwatch sw = Stopwatch.createStarted();
		TaskResult taskRes = TaskResult.forTask(task);
		doRun(task, taskRes);
		logger.info("Task[{}] run {}. ({})", task.getTaskId(), taskRes.isSuccessful() ? "SUCCESS" : "FAILED", sw);
		return taskRes;
	}
	
	@Override
	public int getCorePoolSize() {
		return DEFAULT_CORE_POOL_SIZE;
	}
	
	@Override
	public int getMaxPoolSize() {
		return DEFAULT_MAX_POOL_SIZE;
	}
	
	protected abstract void doRun(final Task task, final TaskResult taskResult);
}
