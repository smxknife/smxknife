package com.smxknife.java2.lock.Lock;

import java.util.concurrent.locks.StampedLock;

/**
 * @author smxknife
 * 2020/7/17
 */
public class StampedLockReentrentDemo {
	private static StampedLock lock = new StampedLock();
	public static void main(String[] args) {
		System.out.println("还没看源码，据网上说StampedLock是不可重入锁，那么先做个实验验证一下");

		new Thread(() -> {
			long stamp = lock.writeLock();
			System.out.println(Thread.currentThread().getName() + " | 第一次获取锁" + stamp);
			long stamp2 = lock.writeLock();
			System.out.println(Thread.currentThread().getName() + " | 第二次获取锁" + stamp2);
		}).start();

		System.out.println("根据实验结果表明，同一个线程确实是无法获取第二把锁");
	}
}
