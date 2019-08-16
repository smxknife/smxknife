package com.smxknife.java2.thread.phaser.demo01;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2018-12-25
 */
public class PrintTools {
	public static Phaser phaser;

	public static void methodA() {
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " A1 begin=" + System.currentTimeMillis());
	}
}
