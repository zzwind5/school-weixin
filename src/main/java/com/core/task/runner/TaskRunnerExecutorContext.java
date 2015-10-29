package com.core.task.runner;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.core.job.Task;
import com.core.job.TaskResult;

import lombok.Getter;

public class TaskRunnerExecutorContext {
	
	private static final long DEF_KEEP_ALIVE_TIMIE = 1;

	@Getter
	private TaskRunner taskRunner;
	
	private ExecutorService executor;
	
	public TaskRunnerExecutorContext(TaskRunner taskRunner) {
		this.taskRunner = taskRunner;
		executor = new ThreadPoolExecutor(taskRunner.getCorePoolSize(), taskRunner.getMaxPoolSize(), 
				DEF_KEEP_ALIVE_TIMIE, TimeUnit.SECONDS, 
				new LinkedBlockingQueue<Runnable>(taskRunner.getCorePoolSize() * 5),
				new CachedCallerBlocksPolicy() );
	}
	
	
	public void execute(final Task task) {
		Runnable taskHandler = new TaskHandler(taskRunner, task);
		executor.submit(taskHandler);
	}
	
	public Future<TaskResult> executeWithResult(final Task task) {
		Callable<TaskResult> taskHandler = new TaskHandler(taskRunner, task);
		return executor.submit(taskHandler);
	}
	
	public String getRunCode() {
		return taskRunner.getRunnerCode();
	}
	
	private static class TaskHandler implements Runnable, Callable<TaskResult> {
		private TaskRunner taskRunner;
		private Task task;
		
		private TaskHandler(final TaskRunner taskRunner, final Task task) {
			this.taskRunner = taskRunner;
			this.task = task;
		}

		@Override
		public void run() {
			taskRunner.run(task);
		}

		@Override
		public TaskResult call() throws Exception {
			return taskRunner.run(task);
		}
		
	}
}
