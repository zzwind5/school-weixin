package com.core.job;

public enum RunMode {

	IN_PROCESS("com.scheduler.job.InProcessTaskRunnerJob"),
	DISTRIBUTED("com.scheduler.job.DistributedTaskRunnerJob");
	
	private final String jobClassName;
	
	private RunMode(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	public Class<?> getJobClass() {
		try {
			return Class.forName(jobClassName);
		} catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Cannot found job class by name " + jobClassName, e);
		}
	}
}
