package com.core.util;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.job.Task;
import com.core.job.TaskResult;
import com.core.task.runner.TaskRunnerExecutorAwareRegistry;

@Slf4j
@Component
public class TaskRunUtil {
	
	private static final long DEFAULT_TIMEOUT = 5L;			//5min

	private static TaskRunnerExecutorAwareRegistry taskExecutorReg;
	
	@Autowired
	private void setTaskExecutorReg(TaskRunnerExecutorAwareRegistry taskExecutorReg){
		TaskRunUtil.taskExecutorReg = taskExecutorReg;
	}
	
	public static void execute(Task task) throws InterruptedException {
		taskExecutorReg.lookUp(task).execute(task);
	}
	
	public static TaskResult executeSyn(Task task) {
		return executeSyn(task, DEFAULT_TIMEOUT, TimeUnit.MINUTES);
	}
	
	public static TaskResult executeSyn(Task task, long timeout, TimeUnit unit) {
		Future<TaskResult> future = taskExecutorReg.lookUp(task).executeWithResult(task);
		try {
			return future.get(timeout, unit);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Execute task failed!", e);
		}
		return null;
	}
}
