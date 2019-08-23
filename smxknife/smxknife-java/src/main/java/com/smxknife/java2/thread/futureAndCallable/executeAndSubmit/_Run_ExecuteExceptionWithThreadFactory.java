package com.smxknife.java2.thread.futureAndCallable.executeAndSubmit;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run_ExecuteExceptionWithThreadFactory {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		executor.setThreadFactory(new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r);
				thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
					@Override
					public void uncaughtException(Thread t, Throwable e) {
						System.out.println("execution 通过 threadFactory自定义异常处理，可以捕获异常了");
						System.out.println(t.getName() + " | " + e.getMessage());
//						e.printStackTrace();
					}
				});
				return thread;
			}
		});

		try {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("execute running ....");
					Integer.parseInt("a");
				}
			});
		} catch (Exception e) {
			// 虽然可以捕获异常，但是是指在threadFactory里面捕获，在execute外层嵌套try catch依然无法在catch中捕获异常
			System.out.println("catch execute | " + e.getMessage());
		}
		executor.shutdown();
	}
}
