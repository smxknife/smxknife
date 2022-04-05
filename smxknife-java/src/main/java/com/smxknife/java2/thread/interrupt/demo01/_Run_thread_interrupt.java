package com.smxknife.java2.thread.interrupt.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/28
 */
public class _Run_thread_interrupt {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			int count = -1;
			@Override
			public void run() {
				while (true) {
					if (++count % 100000000 == 0) {
						System.out.println("count = " + count);
					}
				}
			}
		});

		thread.start();
		thread.interrupt();
		System.out.println("isInterrupted1: " + thread.isInterrupted());
		System.out.println("isInterrupted2: " + thread.isInterrupted());

		thread.interrupt();
		System.out.println("isInterrupted3: " + thread.isInterrupted());
	}
}
