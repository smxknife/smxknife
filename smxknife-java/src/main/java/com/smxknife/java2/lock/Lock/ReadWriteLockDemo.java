package com.smxknife.java2.lock.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author smxknife
 * 2019/9/15
 */
public class ReadWriteLockDemo {
	public static void main(String[] args) throws InterruptedException {

		//singleThreadReadWrite();
		//singleThreadWriteRead();

		multiThreadWriteRead();

	}

	private static void multiThreadWriteRead() throws InterruptedException {
		System.out.println("通过这个方法可以看出，不同线程间是无法实现锁的降级，在一个线程获取写锁的情况下，另一个线程是无法获得读锁");
		ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
		new Thread(() -> {
			readWriteLock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " | 获得写锁");
		}, "m_t_write").start();
		TimeUnit.SECONDS.sleep(3); // 为了让上一个线程执行
		new Thread(() -> {
			readWriteLock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " | 获得读锁");
		}, "m_t_read").start();
	}

	private static void singleThreadWriteRead() {
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " | 该线程是为了测试同一个线程，获取写锁后可以继续获得读锁：即支持锁降级");
			ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
			readWriteLock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " | after writelock lock");
			readWriteLock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " | after readlock lock");
		}, "s_t_write_read").start();
	}

	private static void singleThreadReadWrite() {
		new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + " | 该线程是为了测试同一个线程，获取读锁之后不可以再获取写锁：即不支持锁升级");
			ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
			readWriteLock.readLock().lock();
			System.out.println(Thread.currentThread().getName() + " | after readlock lock");
			readWriteLock.writeLock().lock();
			System.out.println(Thread.currentThread().getName() + " | after writelock lock");
		}, "s_t_read_write").start();
	}
}
