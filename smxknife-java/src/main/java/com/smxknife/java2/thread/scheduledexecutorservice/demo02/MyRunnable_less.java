package com.smxknife.java2.thread.scheduledexecutorservice.demo02;

/**
 * @author smxknife
 * 2019/8/29
 */
public class MyRunnable_less implements Runnable {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " begin " + System.currentTimeMillis());

		System.out.println(Thread.currentThread().getName() + " end " + System.currentTimeMillis());
	}
}
