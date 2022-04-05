package com.smxknife.java2.thread.scheduledexecutorservice.demo05;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/30
 */
public class _Run_default_execute_existing_delayed_task_after_shutdown_policy {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		for (int i = 0; i < 2; i++) {
			executor.schedule(new MyRunnable05("R" + i), 3, TimeUnit.SECONDS);
		}
		// 当调用shutdown，默认情况下，已有任务会继续执行，使用setExecuteExistingDelayedTaskAfterShutdownProxy
		// 为false时，才会不执行，默认是true
		executor.shutdown();
		System.out.println("end");
	}
}
