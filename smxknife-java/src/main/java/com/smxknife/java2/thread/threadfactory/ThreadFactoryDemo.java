package com.smxknife.java2.thread.threadfactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author smxknife
 * 2019/9/19
 */
public class ThreadFactoryDemo {
	public static void main(String[] args) {
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(r, "custom-thread-%d");
			}
		};

		threadFactory.newThread(() -> {
			System.out.println(Thread.currentThread().getName() + " | xxxx");
		}).start();

		ThreadFactory threadFactory1 = Executors.defaultThreadFactory();
		Thread thread = threadFactory1.newThread(() -> {
			System.out.println(Thread.currentThread().getName() + " | yyyy");
		});
		thread.start();
	}
}
