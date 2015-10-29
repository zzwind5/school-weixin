package com.core.task.runner;

import com.core.job.Task;
import com.core.job.TaskResult;

public interface TaskRunner {

	String getRunnerCode();
	
	TaskResult run(final Task task);
	
	int getCorePoolSize();
	
	int getMaxPoolSize();
}
