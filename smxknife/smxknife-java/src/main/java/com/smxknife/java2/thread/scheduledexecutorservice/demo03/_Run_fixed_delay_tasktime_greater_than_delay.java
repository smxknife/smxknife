package com.smxknife.java2.thread.scheduledexecutorservice.demo03;

import com.smxknife.java2.thread.scheduledexecutorservice.demo02.MyRunnable_greater;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class _Run_fixed_delay_tasktime_greater_than_delay {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		System.out.println("Main enter: " + System.currentTimeMillis());
		// 初次延迟1s执行
		// 不管任务执行多久，都是前一个任务执行完后等待2s
		scheduledExecutorService.scheduleWithFixedDelay(new MyRunnable_greater(), 1, 2, TimeUnit.SECONDS);
		System.out.println("Main fin: " + System.currentTimeMillis());
	}
}
