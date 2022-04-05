package com.smxknife.java2.thread.interrupt.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/28
 */
public class _Run_thread_interrupt_with_sleep {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			int count = -1;
			@Override
			public void run() {
				while (true) {
					System.out.println(count++);
					try {
						TimeUnit.SECONDS.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		thread.start();
		thread.interrupt();
		System.out.println("isInterrupted1: " + thread.isInterrupted());
		System.out.println("isInterrupted2: " + thread.isInterrupted());
		System.out.println("isInterrupted3: " + thread.isInterrupted());
		System.out.println("isInterrupted4: " + thread.isInterrupted());

		thread.interrupt();
		System.out.println("isInterrupted5: " + thread.isInterrupted());
	}
}
