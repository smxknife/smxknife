package com.smxknife.java2.lock.locksupport;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

/**
 * @author smxknife
 * 2020/6/4
 */
public class Demo04 {
	public static void main(String[] args) throws InterruptedException {
		lockSupportInterruptTest();
		waitNotifyInterruptTest();
	}

	private static void waitNotifyInterruptTest() throws InterruptedException {
		Object lock = new Object();
		Thread thread = new Thread(() -> {
			synchronized (lock) {
				System.out.println(Thread.currentThread().getName() + " 即将无限阻塞");
				try {
					lock.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " 除非interrupt，我不应该出现");
				System.out.println(Thread.currentThread().getName() + " | " + Thread.currentThread().isInterrupted());
			}
		}, "waitNotify");
		thread.start();
		TimeUnit.SECONDS.sleep(5);
		thread.interrupt();
	}

	private static void lockSupportInterruptTest() throws InterruptedException {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " 即将无限阻塞");
			LockSupport.park();
			System.out.println(Thread.currentThread().getName() + " 除非interrupt，我不应该出现");
			System.out.println(Thread.currentThread().getName() + " | " + Thread.currentThread().isInterrupted());
		}, "lockSupport");
		thread.start();
		TimeUnit.SECONDS.sleep(3);
		thread.interrupt();
	}
}
