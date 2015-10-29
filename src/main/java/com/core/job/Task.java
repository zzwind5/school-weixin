package com.core.job;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Task implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	private String taskId = UUID.randomUUID().toString();
	private String taskName;
	private JobSpec jobSpec = new JobSpec();
}
