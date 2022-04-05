package com.smxknife.energy.services.provider;

import java.io.IOException;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author smxknife
 * 2021/7/9
 */
public class ThreadOutpu {
	public static void main(String[] args) throws IOException {

		ReentrantLock lock = new ReentrantLock(true);

		final Condition conditionA = lock.newCondition();
		final Condition conditionB = lock.newCondition();
		final Condition conditionC = lock.newCondition();

		Thread th1 = new Thread(() -> {

			while (true) {
				System.out.println("A");
				lock.lock();
				try {

					conditionB.signal();
					conditionA.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}, "th1");

		Thread th2 = new Thread(() -> {
			while (true) {
				System.out.println("B");
				lock.lock();
				try {
					conditionC.signal();
					conditionB.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});

		Thread th3 = new Thread(() -> {
			while (true) {
				System.out.println("C");
				lock.lock();
				try {
					conditionA.signal();
					conditionC.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		});

		th1.start();
		th2.start();
		th3.start();

		System.in.read();


	}
}
