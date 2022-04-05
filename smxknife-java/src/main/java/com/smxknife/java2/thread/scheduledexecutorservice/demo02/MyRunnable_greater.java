package com.smxknife.java2.thread.scheduledexecutorservice.demo02;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/29
 */
public class MyRunnable_greater implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " begin " + LocalTime.now());

		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(Thread.currentThread().getName() + " end " + LocalTime.now());
	}
}
