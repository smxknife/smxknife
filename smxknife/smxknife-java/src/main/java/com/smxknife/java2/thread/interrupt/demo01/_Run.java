package com.smxknife.java2.thread.interrupt.demo01;

import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/25
 */
public class _Run {
	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getState());
			while (true) {
				if (Thread.currentThread().isInterrupted()) {
					System.out.println("虽然我被中断了，但是会一直运行下去");
					System.out.println("interrupt, running");
				} else {
					System.out.println("normal");
				}
			}
		});

		thread.start();
		TimeUnit.SECONDS.sleep(2);
		thread.interrupt();
	}
}
