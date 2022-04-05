package com.smxknife.java2.thread.countdownlatch.demo02;

import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyThread extends Thread {

	private CountDownLatch maxRunner;

	public MyThread(CountDownLatch maxRunner) {
		this.maxRunner = maxRunner;
	}

	@Override
	public void run() {
		try {
			System.out.println(Thread.currentThread().getName() + " count : " + maxRunner.getCount());
			Thread.sleep(2000);
			maxRunner.countDown();
			System.out.println(Thread.currentThread().getName() + " is prepared! current count : " + maxRunner.getCount());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
