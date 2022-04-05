package com.smxknife.java2.thread.thread._06join;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/6/11
 */
public class _Run_Jvm_lock {

	private static volatile boolean needYield = true;

	public static void main(String[] args) throws InterruptedException, IOException {

		TimeUnit.SECONDS.sleep(15);

		Object lock = new Object();

		Thread th1 = new Thread(() -> {
//			System.out.println(Thread.currentThread().getName());
//			while (needYield) {
//				Thread.yield();
//			}
			System.out.println(Thread.currentThread().getName() + " | begin to get lock");
			synchronized (lock) {
				for (int i = 0; i < 10; i++) {
					System.out.println(Thread.currentThread().getName());
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}, "join_jvm_view1");

		Thread th2 = new Thread(() -> {
			int m = 0, n = 10;
			synchronized (lock) {
				th1.start();
				while (true) {
//					for (int i = 0; i < 100000000; i++) {
//						Math.random();
//					}
					System.out.println(Thread.currentThread().getName());
					if (m++ > n) {
						try {
							needYield = false;
							th1.join();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}, "join_jvm_view2");
		th2.start();

		System.in.read();
	}
}
