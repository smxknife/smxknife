package com.smxknife.java2.thread.futureAndCallable.rejectHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run {
	public static void main(String[] args) {
		// RejectedExecutionHandler是当线程池关闭后，依然有任务要执行时，可以实现的一些处理

		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(3);
		executor.setRejectedExecutionHandler(new MyRejectedExecutionHandler());

		executor.submit(() -> System.out.println(Thread.currentThread().getName() + " is running..."));
		executor.submit(() -> System.out.println(Thread.currentThread().getName() + " is running..."));
		executor.submit(() -> System.out.println(Thread.currentThread().getName() + " is running..."));
		executor.submit(() -> System.out.println(Thread.currentThread().getName() + " is running..."));
		executor.shutdown();
		executor.submit(() -> Thread.currentThread().getName() + " is running...");
		executor.submit(() -> Thread.currentThread().getName() + " is running...");

	}
}
