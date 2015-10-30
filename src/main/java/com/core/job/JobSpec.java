package com.core.job;


import static com.google.common.base.Preconditions.checkNotNull;

import com.core.util.JsonUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class JobSpec {

	private RunMode runMode = RunMode.DISTRIBUTED;
	private JobGroup group;
	private String name;
	private String jsonJobParams;
	private String runnerCode;
	
	@JsonIgnore
	public <T extends JobParams> T getJobParams(final Class<T> clazz) {
		return JsonUtil.toObject(jsonJobParams, clazz);
	}
	
	@JsonIgnore
	public <T extends JobParams> void setTaskParams(final T params) {
		this.jsonJobParams = JsonUtil.toJsonString(params);
	}
	
	@JsonIgnore
    public String getGroupName() {
        return group == null ? null : group.name();
    }
	
	public void validate() {
    	checkNotNull(group);
        checkNotNull(name);
        checkNotNull(runnerCode);
    }
}
