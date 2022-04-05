package com.smxknife.java2.thread.scheduledexecutorservice.demo03;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class MyRunnable_greater implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
	}
}
