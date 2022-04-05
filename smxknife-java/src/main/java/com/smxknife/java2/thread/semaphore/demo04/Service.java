package com.smxknife.java2.thread.semaphore.demo04;

import java.util.concurrent.Semaphore;

/**
 * @author smxknife
 * 2018-12-21
 */
public class Service {

	private Semaphore semaphore = new Semaphore(1);

	public void testInterruptMethod() {
		try {
			semaphore.acquire();
			System.out.println(Thread.currentThread().getName() + " begin time : " + System.currentTimeMillis());
			for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
				String string = new String();
				Math.random();
			}
			System.out.println(Thread.currentThread().getName() + " end time : " + System.currentTimeMillis());
			semaphore.release();
		} catch (InterruptedException e) {
			System.out.println(" 线程 " + Thread.currentThread().getName() + " 进入catch");
			e.printStackTrace();
		}
	}

	public void testUnInterruptMethod() {
		try {
			semaphore.acquireUninterruptibly();
			System.out.println(Thread.currentThread().getName() + " begin time : " + System.currentTimeMillis());
			for (int i = 0; i < Integer.MAX_VALUE / 50; i++) {
				String string = new String();
				Math.random();
			}
			System.out.println(Thread.currentThread().getName() + " end time : " + System.currentTimeMillis());
			semaphore.release();
		} catch (Exception e) {
			System.out.println(" 线程 " + Thread.currentThread().getName() + " 进入catch");
			e.printStackTrace();
		}
	}
}
