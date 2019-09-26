package com.smxknife.java2.thread.yield;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {

	public static void main(String[] args) {

		// yield()从未导致线程转到等待/睡眠/阻塞状态。
		// 在大多数情况下，yield()将导致线程从运行状态转到可运行状态，但有可能没有效果。

		Runnable runnable = () -> {
			for (int i = 0; i < 100; i++) {
				System.out.println("index = " + i + " name = " + Thread.currentThread().getName());
				if ("t1".equals(Thread.currentThread().getName()) && i == 5) {
					System.out.println(Thread.currentThread().getName() + " yield at index = " + i);
					Thread.yield();
				}
			}
		};

		new Thread(runnable, "t1").start();
		new Thread(runnable, "t2").start();
	}
}
