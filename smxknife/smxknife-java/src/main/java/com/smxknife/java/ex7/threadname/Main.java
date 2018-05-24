package com.smxknife.java.ex7.threadname;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
	public static void main(String[] args) {
//		Runnable runnable = () -> {
//			while (true) {
//				System.out.println(Thread.currentThread().getName());
//				try {
//					Thread.sleep(1000L * 3);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		};
//
//		runnable.run();

//		TestThread thread1 = new TestThread();
//		TestThread thread2 = new TestThread();
//
//		thread1.run();
//		thread2.run();

//		Executors.newFixedThreadPool(5)
		ExecutorService newFixedThreadPool = Executors.newCachedThreadPool();
		System.out.println("****************************newFixedThreadPool*******************************");
		for(int i=0;i<4;i++)
		{
			final int index=i;
			System.out.println(newFixedThreadPool.toString());
			newFixedThreadPool.execute(() -> {
				System.out.println(Thread.currentThread().getName());
			});
		}
//		newFixedThreadPool.shutdown();
	}
}
