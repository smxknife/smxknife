package com.smxknife.java2.thread.thread._04sleep;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/6/11
 */
public class _Run_Jvm_lock {
	public static void main(String[] args) {
		Object lock = new Object();
		new Thread(() -> {
			synchronized (lock) {
				while (true) {
					try {
						for (int i = 0; i < 100000000; i++) {
							Math.random();
						}
						System.out.println(Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}, "sleep_jvm_view1").start();

		new Thread(() -> {
			synchronized (lock) {
				while (true) {
					try {
						for (int i = 0; i < 100000000; i++) {
							Math.random();
						}
						System.out.println(Thread.currentThread().getName());
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "sleep_jvm_view2").start();
	}
}
