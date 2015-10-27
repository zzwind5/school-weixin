package com.core.task;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class TaskResult implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private String taskId;
	private boolean successful;
	private Date startTime = new Date();
	private Date endTime;
	private String jsonResult;
	private String resutlMessage;
	
	public static TaskResult forTask(final Task task) {
		return forTask(task.getTaskId());
	}
	
	public static TaskResult forTask(final String taskId) {
		TaskResult taskResult = new TaskResult();
		taskResult.setTaskId(taskId);
		return taskResult;
	}
}
