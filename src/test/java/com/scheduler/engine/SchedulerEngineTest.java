package com.scheduler.engine;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class SchedulerEngineTest {

	@Test
	public void waiting() throws InterruptedException{
		Thread.sleep(1000 * 60 * 5);
	}
}
