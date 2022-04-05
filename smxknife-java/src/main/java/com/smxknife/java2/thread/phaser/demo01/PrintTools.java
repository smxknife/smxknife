package com.smxknife.java2.thread.phaser.demo01;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2018-12-25
 */
public class PrintTools {
	public static Phaser phaser;

	public static void methodA() {
		System.out.println(Thread.currentThread().getName() + " A1 begin=" + System.currentTimeMillis());
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " A1 end=" + System.currentTimeMillis());

		System.out.println(Thread.currentThread().getName() + " A2 begin=" + System.currentTimeMillis());
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " A2 end=" + System.currentTimeMillis());
	}

	public static void methodB() {
		try {
			System.out.println(Thread.currentThread().getName() + " B1 begin=" + System.currentTimeMillis());
			Thread.sleep(5000);
			phaser.arriveAndDeregister();
			System.out.println(Thread.currentThread().getName() + " B1 end=" + System.currentTimeMillis());

			System.out.println(Thread.currentThread().getName() + " B2 begin=" + System.currentTimeMillis());
			Thread.sleep(5000);
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + " B2 end=" + System.currentTimeMillis());
		} catch (InterruptedException e) {

		}
	}
}
