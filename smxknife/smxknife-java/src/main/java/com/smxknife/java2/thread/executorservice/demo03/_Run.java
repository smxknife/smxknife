package com.smxknife.java2.thread.executorservice.demo03;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/3/6
 */
public class _Run {
	public static void main(String[] args) {
		_Run run = new _Run();
		run.test();
	}

	public void test() {

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
		scheduledExecutorService.scheduleAtFixedRate(this::recycle, 3, 1, TimeUnit.SECONDS);
	}

	private void recycle() {
		System.out.println("---- " + Thread.currentThread().getName());
		new Thread(() -> {
			System.out.println("***** " + Thread.currentThread().getName());
		}, "inner_thread").start();
		// 通过运行结果会发现，new Thread根本不在线程池内运行
	}


}
