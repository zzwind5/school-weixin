package com.core.task;

import java.io.Serializable;
import java.util.UUID;

import com.core.util.JsonUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private String taskId = UUID.randomUUID().toString();
	private String taskName;
	private String jsonTaskParams;
	private String runnerCode;
	
	public <T> T getTaskParams(final Class<T> clazz) {
		return JsonUtil.toObject(jsonTaskParams, clazz);
	}
	
	public void setTaskParams(final Object params) {
		this.jsonTaskParams = JsonUtil.toJsonString(params);
	}
}
