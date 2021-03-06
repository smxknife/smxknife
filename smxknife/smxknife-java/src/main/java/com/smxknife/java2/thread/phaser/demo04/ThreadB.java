package com.smxknife.java2.thread.phaser.demo04;

import lombok.AllArgsConstructor;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2019/8/26
 */
@AllArgsConstructor
public class ThreadB extends Thread {

	private Phaser phaser;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " B1 begin = " + System.currentTimeMillis());
		phaser.arriveAndAwaitAdvance();
		System.out.println(Thread.currentThread().getName() + " B2 end = " + System.currentTimeMillis());
	}
}
