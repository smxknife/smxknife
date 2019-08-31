package com.smxknife.java2.thread.scheduledexecutorservice.demo02;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class _Run_fixed_rate_tasktime_greater_than_rate {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		System.out.println("Main enter: " + System.currentTimeMillis());
		// 初次延迟1s执行
		// 后面，如果任务能在2s内执行完，那么需要等待2s
		// 如果任务执行完时间大于2s，那么下一个任务会立刻执行
		scheduledExecutorService.scheduleAtFixedRate(new MyRunnable_greater(), 1, 2, TimeUnit.SECONDS);
		System.out.println("Main fin: " + System.currentTimeMillis());
	}
}
