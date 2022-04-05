package com.smxknife.java2.thread.waitnotify;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/6/11
 */
public class _Run_Jvm {
	public static void main(String[] args) throws InterruptedException {
		TimeUnit.SECONDS.sleep(10);
		Object lock = new Object();
		new Thread(() -> {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " ... ");
				for (int i = 0; i < 100000000; i++) {
					Math.random();
				}
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "wait_jvm_view").start();

		new Thread(() -> {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " !!! ");
				for (int i = 0; i < 100000000; i++) {
					Math.random();
				}
				try {
					lock.wait(300000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "wait_jvm_view_timeout").start();
	}
}
