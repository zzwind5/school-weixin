package com.core.task.runner;

public interface TaskRunnerRegistry {

	TaskRunner lookUp(final String runnerCode);
}
