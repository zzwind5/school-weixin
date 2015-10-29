package com.core.job;

import com.core.util.JsonUtil;

import lombok.Data;

@Data
public class JobSpec {

	private JobGroup jobGroup;
	private String name;
	private String jsonJobParams;
	private String runnerCode;
	
	public <T extends JobParams> T getJobParams(final Class<T> clazz) {
		return JsonUtil.toObject(jsonJobParams, clazz);
	}
	
	public <T extends JobParams> void setTaskParams(final T params) {
		this.jsonJobParams = JsonUtil.toJsonString(params);
	}
}
