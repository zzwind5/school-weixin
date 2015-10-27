package com.core.task.runner;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.core.task.Task;

public class TaskRunnerExecutor {

	private TaskRunner taskRunner;
	
	private ThreadPoolExecutor executor;
	
	public TaskRunnerExecutor(TaskRunner taskRunner) {
		this.taskRunner = taskRunner;
		executor = new ThreadPoolExecutor(taskRunner.getCorePoolSize(), taskRunner.getMaxPoolSize(), 
				60, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
	}
	
	public void execute(final Task task) {
		TaskRunnable runAb = new TaskRunnable(taskRunner, task);
		executor.execute(runAb);
	}
	
	private class TaskRunnable implements Runnable {
		private TaskRunner taskRunner;
		private Task task;
		
		TaskRunnable(final TaskRunner taskRunner, final Task task) {
			this.taskRunner = taskRunner;
			this.task = task;
		}

		@Override
		public void run() {
			taskRunner.run(task);
		}
		
	}
}
