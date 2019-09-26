package com.smxknife.java2.thread.threadlock;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/19
 */
public class StaticInstanceObj {

	public synchronized static void classInit() {
		System.out.println(Thread.currentThread().getName() + " classInit");
//		try {
//			TimeUnit.SECONDS.sleep(1);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
		System.out.println(Thread.currentThread().getName() + " classInit end");
	}

	public synchronized void doSome() {
		System.out.println(Thread.currentThread().getName() + " doSome");
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " doSome end");
	}

	public synchronized void doAnother() {
		System.out.println(Thread.currentThread().getName() + " doAnother");

		System.out.println(Thread.currentThread().getName() + " doAnother end");
	}
}
