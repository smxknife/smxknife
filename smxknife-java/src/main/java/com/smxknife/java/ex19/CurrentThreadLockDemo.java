package com.smxknife.java.ex19;

/**
 * @author smxknife
 * 2018/11/19
 */
public class CurrentThreadLockDemo implements Runnable{
	public static void main(String[] args) {
		Thread threadA = Thread.currentThread();
		Thread threadB = Thread.currentThread();

		System.out.println(Thread.currentThread().getState());
		threadA.start();
		threadB.start();

	}

	@Override
	public void run() {
		test();
	}

	public synchronized void test() {
		try {
			System.out.println(Thread.currentThread().getName());
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
