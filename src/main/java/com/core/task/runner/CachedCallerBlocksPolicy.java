package com.core.task.runner;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CachedCallerBlocksPolicy implements RejectedExecutionHandler {

	private static final Logger logger = LoggerFactory.getLogger(CachedCallerBlocksPolicy.class);
	private static final int MAX_WAIT = 1000 * 60;
	
	private ThreadPoolExecutor runnableCachePool;

	/**
	 * @param maxWait The maximum time to wait for a queue slot to be
	 * available, in milliseconds.
	 */
	public CachedCallerBlocksPolicy() {
		runnableCachePool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
	}

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		if (!executor.isShutdown()) {
			try {
				if (logger.isDebugEnabled()) {
					logger.debug("Attempting to queue task execution for " + MAX_WAIT + " milliseconds");
				}
				
				RunnableHolder runnableHolder = new RunnableHolder(r, executor);
				runnableCachePool.execute(runnableHolder);
				
				System.out.println("Runnable cache pool size: " + runnableCachePool.getQueue().size());
				
				if (logger.isDebugEnabled()) {
					logger.debug("Task execution queued");
				}
			}
			catch (Exception e) {
				Thread.currentThread().interrupt();
				throw new RejectedExecutionException("Interrupted", e);
			}
		}
		else {
			throw new RejectedExecutionException("Executor has been shut down");
		}
	}
	
	private static class RunnableHolder implements Runnable {
		
		private Runnable r;
		private ThreadPoolExecutor executor;
		
		private RunnableHolder(Runnable r, ThreadPoolExecutor executor) {
			this.r = r;
			this.executor = executor;
		}

		@Override
		public void run() {
			try {
				if (!executor.getQueue().offer(r, MAX_WAIT, TimeUnit.MILLISECONDS)) {
					throw new RejectedExecutionException("Max wait time expired to queue task");
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);
			}
		}
	}

}
