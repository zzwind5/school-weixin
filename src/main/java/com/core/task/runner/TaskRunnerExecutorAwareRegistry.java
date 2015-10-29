package com.core.task.runner;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.core.job.Task;
import com.google.common.base.Stopwatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TaskRunnerExecutorAwareRegistry implements ApplicationContextAware {
	
	private final Map<String, TaskRunnerExecutorContext> runnerMap = new HashMap<>();

	@Override
	public void setApplicationContext(ApplicationContext application) throws BeansException {
		log.info("Searching TaskRunner...");
		Stopwatch sw = Stopwatch.createStarted();
		
		Map<String, TaskRunner> runners = application.getBeansOfType(TaskRunner.class);
		for (TaskRunner runner : runners.values()) {
			String runCode = runner.getRunnerCode();
			
			if (runnerMap.containsKey(runCode)) {
				TaskRunner existsRunner = runnerMap.get(runCode).getTaskRunner();
				throw new IllegalArgumentException(String.format("Duplicate task runner code [%s] for %s and %s", 
						runCode, runner.getClass(), existsRunner.getClass()));
			}
			
			log.info("Registered {} => {}");
			runnerMap.put(runCode, new TaskRunnerExecutorContext(runner));
		}
		log.info("Registered {} TaskRunner. ({})", runnerMap.size(), sw);
	}

	public TaskRunnerExecutorContext lookUp(final Task task) {
		try {
			TaskRunnerExecutorContext runnerCtx = runnerMap.get(task.getJobSpec().getRunnerCode());
			if (runnerCtx == null) {
				throw new IllegalArgumentException("Unrecognized task runner code: " + task.getJobSpec().getRunnerCode());
			}
			return runnerMap.get(task.getJobSpec().getRunnerCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
