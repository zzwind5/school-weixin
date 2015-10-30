package com.core.job;

import static com.google.common.base.Preconditions.checkNotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class JobRequest {

	private String description;
	
	private JobSpec spec;
	
	private JobTrigger trigger;
	
	@JsonIgnore
    public String getName() {
        return spec == null ? null : spec.getName();
    }

    @JsonIgnore
    public JobGroup getGroup() {
        return spec == null ? null : spec.getGroup();
    }

    @JsonIgnore
    public String getGroupName() {
        return spec == null ? null : spec.getGroupName();
    }

    @JsonIgnore
    public String getCompositeName() {
        return getGroupName() + "." + getName();
    }

    public void validate() {
    	checkNotNull(spec);
        spec.validate();
        checkNotNull(spec);
    }
}
