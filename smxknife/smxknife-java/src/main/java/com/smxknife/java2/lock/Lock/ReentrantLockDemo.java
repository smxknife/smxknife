package com.smxknife.java2.lock.Lock;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smxknife
 * 2019/9/12
 */
public class ReentrantLockDemo {
	static ReentrantLock lock = new ReentrantLock();
	public static void main(String[] args) {
		new Thread(() -> test(), "th-A").start();
		new Thread(() -> test(), "th-B").start();

	}

	private static void test() {
		lock.lock();
		try {
			System.out.println(Thread.currentThread().getName() + " _ test");
			LockSupport.parkUntil(LocalDateTime.now().plusSeconds(3).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
			System.out.println(Thread.currentThread().getName() + " go _ on");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
	}
}
