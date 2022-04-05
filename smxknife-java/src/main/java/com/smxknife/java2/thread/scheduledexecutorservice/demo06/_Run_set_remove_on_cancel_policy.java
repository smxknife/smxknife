package com.smxknife.java2.thread.scheduledexecutorservice.demo06;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/30
 */
public class _Run_set_remove_on_cancel_policy {
	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

		MyRunnable06 r1 = new MyRunnable06("R1");
		ScheduledFuture<?> r1Future = executor.schedule(r1, 1, TimeUnit.SECONDS);

		// 设置为true之后，任务会从队列中移除
		executor.setRemoveOnCancelPolicy(true);

		System.out.println(r1Future.cancel(true));
		System.out.println();

		executor.getQueue().stream().forEach(runnable -> {
			System.out.println("队列 中： " + runnable);
		});
		System.out.println("end");
	}
}
