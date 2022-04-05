package com.smxknife.java.ex27;

import java.util.concurrent.Semaphore;

/**
 * @author smxknife
 * 2019-06-04
 */
public class UtilTest {
	private static Object lock = new Object();

	private static Semaphore semaphore = new Semaphore(1);

	public static void test() {

		synchronized (lock) {
			System.out.println(Thread.currentThread().getName());
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " is running in " + i);
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void test2() {
		try {
			semaphore.acquire();
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName() + " is running in " + i);
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		semaphore.release();
	}
}
