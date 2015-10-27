package com.core.task.runner;

import com.core.task.Task;
import com.core.task.TaskResult;

public interface TaskRunner {

	String getRunnerCode();
	
	TaskResult run(final Task task);
	
	int getCorePoolSize();
	
	int getMaxPoolSize();
}
