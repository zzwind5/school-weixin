package com.core.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.core.job.Task;
import com.core.job.TaskResult;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TaskRunUtilTest {
	
//	private Task task;
//	
//	@Before
//	public void dataPrepare(){
//		Task task = new Task();
//		task.setTaskName("Unite Test");
//		task.setRunnerCode("TempRunnerForTest");
//	}
//	

	@Test
	public void executeSynTest() throws InterruptedException{
		for (int i=0; i<100; i++) {
			Task task = new Task();
			task.setTaskName("Unite Test " + i);
			task.getJobSpec().setRunnerCode("TempRunnerForTest");
			TaskRunUtil.execute(task);
		}
		
		Task task = new Task();
		task.setTaskName("Unite Test syn task ");
		task.getJobSpec().setRunnerCode("TempRunnerForTest");
		TaskResult result = TaskRunUtil.executeSyn(task);
		if (result != null) {
			System.out.println(result.getJsonResult());
		}
	}
	
	
//	@Test
//	public void executeTest(){
//		TaskRunUtil.execute(task);
//	}
}
