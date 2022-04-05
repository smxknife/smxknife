package com.smxknife.java2.thread.executorservice.demo06;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/31
 */
public class WithExceptionThreadPoolExecutor extends ThreadPoolExecutor {

	public WithExceptionThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
		super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
	}

	@Override
	protected void afterExecute(Runnable r, Throwable t) {
		System.out.println(r.getClass());
		System.out.println(t.getMessage());
	}
}
