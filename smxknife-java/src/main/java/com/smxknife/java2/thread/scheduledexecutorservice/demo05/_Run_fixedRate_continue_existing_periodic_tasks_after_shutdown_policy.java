package com.smxknife.java2.thread.scheduledexecutorservice.demo05;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/30
 */
public class _Run_fixedRate_continue_existing_periodic_tasks_after_shutdown_policy {
	public static void main(String[] args) throws InterruptedException {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
		// KEY
		// 注意这个方法只能与schedule和shutdown联合使用，与scheduleAtFixedRate是没有效果的
		// 无论true还是false，只要shutdown，立刻放弃执行
		// executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(true);

		// KEY
		// setExecuteExistingDelayedTasksAfterShutdownPolicy默认是false，如果更改为true
		// 那么任务会继续执行
		executor.setContinueExistingPeriodicTasksAfterShutdownPolicy(true);
		for (int i = 0; i < 2; i++) {
			executor.scheduleAtFixedRate(new MyRunnable05("R" + i), 3, 2, TimeUnit.SECONDS);
		}

		TimeUnit.SECONDS.sleep(5);

		// 当调用shutdown，默认情况下，已有任务会继续执行，使用setExecuteExistingDelayedTaskAfterShutdownProxy
		// 为false时，才会不执行，默认是true
		executor.shutdown();
		System.out.println("end");
	}
}
