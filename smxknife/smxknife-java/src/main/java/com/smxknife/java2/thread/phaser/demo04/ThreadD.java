package com.smxknife.java2.thread.phaser.demo04;

import lombok.AllArgsConstructor;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/26
 */
@AllArgsConstructor
public class ThreadD extends Thread {

	private Phaser phaser;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " D1 begin = " + System.currentTimeMillis());

		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " D2 end = " + System.currentTimeMillis());
	}
}
