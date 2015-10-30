package com.core.task.runner.impl;

import org.springframework.stereotype.Component;

import com.core.job.Task;
import com.core.job.TaskResult;
import com.core.task.runner.AbstractorTaskRunner;

@Component
public class TempRunnerForTest extends AbstractorTaskRunner {

	@Override
	protected void doRun(Task task, TaskResult taskResult) {
		System.out.println(String.format("Task (%s) start [%s] .....", task.getJobSpec().getName(), Thread.currentThread().getName()));
		try {
			Thread.sleep(1000 * 10);
			taskResult.setJsonResult("Hello World!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(String.format("************Task (%s) execute successful.", task.getJobSpec().getName()));
	}

}
