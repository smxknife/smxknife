package com.smxknife.java2.thread.countdownlatch.demo01;

import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyService {

	private CountDownLatch down = new CountDownLatch(1);

	public void testM() {
		try {
			System.out.println(Thread.currentThread().getName() + " - A");
			down.await();
			System.out.println(Thread.currentThread().getName() + " - B");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void downM() {
		System.out.println("x");
		System.out.println("current count" + down.getCount());
		down.countDown();
	}
}
