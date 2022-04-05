package com.smxknife.java2.thread.phaser.demo05;

import lombok.AllArgsConstructor;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2019/8/27
 */
@AllArgsConstructor
public class MyThread extends Thread {

	private Phaser phaser;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " A1 begin = " + System.currentTimeMillis());
		phaser.awaitAdvance(0);
		System.out.println(Thread.currentThread().getName() + " A1 end = " + System.currentTimeMillis());

	}
}
