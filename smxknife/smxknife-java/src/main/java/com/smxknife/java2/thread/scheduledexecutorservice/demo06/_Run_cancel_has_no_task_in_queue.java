package com.smxknife.java2.thread.scheduledexecutorservice.demo06;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/30
 */
public class _Run_cancel_has_no_task_in_queue {
	public static void main(String[] args) throws InterruptedException {
		ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(10);

		MyRunnable06 r1 = new MyRunnable06("R1");
		// 0表示不放入队列，立马执行
		ScheduledFuture<?> r1Future = executor.schedule(r1, 0, TimeUnit.SECONDS);

		TimeUnit.SECONDS.sleep(3);

		System.out.println("A queue size = " + executor.getQueue().size());

		System.out.println("取消结果：   " + r1Future.cancel(true));

		System.out.println("B queue size = " + executor.getQueue().size());
		System.out.println("end");
	}
}
