package com.scheduler.job;

import com.core.job.Task;
import com.core.job.TaskResult;
import com.core.util.TaskRunUtil;

public class DistributedTaskRunnerJob extends AbstractTaskRunnerJob {

	@Override
	TaskResult run(Task task) {
		TaskRunUtil.execute(task);
		return null;
	}
	
}
