package com.smxknife.java2.thread.semaphore.demo02;

import java.util.concurrent.Semaphore;

/**
 * @author smxknife
 * 2018-12-21
 */
public class Service {

	/**
	 * permits 许可，同一时间，最多允许多少个线程同时执行acquire和release之间的代码
	 */
	private Semaphore semaphore = new Semaphore(10);

	public void testMethod() {
		try {
			semaphore.acquire(2);
			System.out.println(Thread.currentThread().getName() + " begin time : " + System.currentTimeMillis());
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getName() + " end time : " + System.currentTimeMillis());
			semaphore.release(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
