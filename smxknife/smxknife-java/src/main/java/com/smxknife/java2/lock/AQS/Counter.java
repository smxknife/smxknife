package com.smxknife.java2.lock.AQS;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/6/3
 */
public class Counter {
	private volatile int num = 0;
	private ExclusiveLock lock = new ExclusiveLock();
	private SharedLock sharedLock = new SharedLock(3);

	public int increment() throws InterruptedException {
		int v = num;
		// 模拟操作耗时，放大效果
		TimeUnit.MILLISECONDS.sleep(1);
		num = num + 1;
		System.out.println(Thread.currentThread() + " after | v = " + v + " | num = " + num);
		return num;
	}

	public int exclusiveIncrement() throws InterruptedException {
		lock.lock();
		try {
			int v = num;
			// 模拟操作耗时，放大效果
			TimeUnit.MILLISECONDS.sleep(1);
			num = num + 1;
			System.out.println(Thread.currentThread() + " after | v = " + v + " | num = " + num);
		} finally {
			lock.unlock();
			return num;
		}
	}

	public int sharedIncrement() throws InterruptedException {
		sharedLock.lock();
		try {
			int v = num;
			// 模拟操作耗时，放大效果
			TimeUnit.MILLISECONDS.sleep(1);
			num = num + 1;
			System.out.println(Thread.currentThread() + " after | v = " + v + " | num = " + num);
		} finally {
			sharedLock.unlock();
			return num;
		}
	}
}
