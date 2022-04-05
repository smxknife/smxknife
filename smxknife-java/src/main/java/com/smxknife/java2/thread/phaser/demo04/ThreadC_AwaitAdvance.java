package com.smxknife.java2.thread.phaser.demo04;

import lombok.AllArgsConstructor;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/26
 */
@AllArgsConstructor
public class ThreadC_AwaitAdvance extends Thread {

	private Phaser phaser;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " C1 begin = " + System.currentTimeMillis());

		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		phaser.awaitAdvance(5); // 如果getPhase() == 5则等待，否则，直接运行

		System.out.println(Thread.currentThread().getName() + " C2 end = " + System.currentTimeMillis());
	}
}
