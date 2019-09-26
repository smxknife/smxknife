package com.smxknife.java2.thread.join;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/25
 */
public class _Run {
	public static void main(String[] args) throws InterruptedException {

		Thread th1 = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + "- no." + i);
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "th1");

		th1.start();

		// 这行代码时关键，如果去掉这行代码，那么main线程和th1线程同时执行，由于main的执行速度快，会优先执行完，输出"end main"
		// 但是调用join之后，就相当于把main（父线程）挂起，等待th1运行完毕后继续执行
		th1.join();

		System.out.println("end main");

	}
}
