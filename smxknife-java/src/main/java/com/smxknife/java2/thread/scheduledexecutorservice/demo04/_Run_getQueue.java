package com.smxknife.java2.thread.scheduledexecutorservice.demo04;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/30
 */
public class _Run_getQueue {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);

		for (int i = 0; i < 5; i++) {
			MyRunnable04 run = new MyRunnable04("R" + i);
			System.out.println(run.hashCode());
			scheduledThreadPoolExecutor.scheduleAtFixedRate(run, 10, 2, TimeUnit.SECONDS);
		}

		System.out.println();

		scheduledThreadPoolExecutor.getQueue().stream().forEach(runnable -> {
			System.out.println("队列中的：" + runnable);
		});
	}
}
