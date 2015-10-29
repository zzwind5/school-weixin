package com.core.util;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextHolder implements ApplicationContextAware {

	private static volatile ApplicationContext applicationContext;
	private static CountDownLatch latch = new CountDownLatch(1);

	@Override
	public void setApplicationContext(final ApplicationContext appCtx) throws BeansException {
		ApplicationContextHolder.applicationContext = appCtx;
		ApplicationContextHolder.latch.countDown();
	}
	
	/**
     * Autowire spring managed dependencies to specific bean.
     * The operation will be blocked until the {@linke ApplicationContextAware#setApplicationContext} was invoked, i.e.
     * the {@code ApplicationContext} becomes available.
     */
	public static void autowire(final Object bean){
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		applicationContext.getAutowireCapableBeanFactory().autowireBean(bean);
	}
}
