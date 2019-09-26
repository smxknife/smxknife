package com.smxknife.java2.thread.waitnotify;

import lombok.ToString;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/24
 */
@ToString
public class Obj {

	private Object lock = new Object();

	public synchronized void doWait() {
		System.out.println(Thread.currentThread().getName() + " enter doWait...");
		try {
			TimeUnit.SECONDS.sleep(2);
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " quit doWait...");
	}

	public synchronized void doNotify() {
		System.out.println(Thread.currentThread().getName() + " enter doNotify...");
		this.notify();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " quit doNotify...");
	}

	public synchronized void doSleep() {
		System.out.println(Thread.currentThread().getName() + " enter doSleep...");
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " quit doSleep...");
	}

	public void doWaitNoLock() {
		System.out.println(Thread.currentThread().getName() + " enter doWaitNoLock...");
		try {
			this.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " quit doWait...");
	}

	public void doNotifyNoLock() {
		System.out.println(Thread.currentThread().getName() + " enter doNotifyNoLock...");
		this.notify();
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + " quit doNotify...");
	}

	public void doWaitWithInnerObjectLock() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " enter doWaitWithInnerObjectLock...");
			try {
				lock.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " quit doWaitWithInnerObjectLock...");
		}
	}

	public void doNotifyWithInnerObjectLock() {
		synchronized (lock) {
			System.out.println(Thread.currentThread().getName() + " enter doNotifyWithInnerObjectLock...");
			lock.notify();
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " quit doNotifyWithInnerObjectLock...");
		}

	}
}
