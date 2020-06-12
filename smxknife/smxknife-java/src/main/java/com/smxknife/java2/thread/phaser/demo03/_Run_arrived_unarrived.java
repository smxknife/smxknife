package com.smxknife.java2.thread.phaser.demo03;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/8/26
 */
public class _Run_arrived_unarrived {
	public static void main(String[] args) throws InterruptedException {
		Phaser phaser = new Phaser(7) {
			@Override
			public int arrive() {
				System.out.println(Thread.currentThread().getName() + " arrive");
				return super.arrive();
			}
		};
		MyThread[] myThread = new MyThread[5];
		Stream.iterate(0, i -> i + 1).limit(5).forEach(idx -> {
			MyThread thread = new MyThread(phaser);
			thread.setName("thread-" + idx);
			thread.start();
		});

		TimeUnit.SECONDS.sleep(2);
		System.out.println("1. 已到达：" + phaser.getArrivedParties());
		System.out.println("2. 未到达：" + phaser.getUnarrivedParties());

		phaser.arrive();
//		phaser.arrive();
		System.out.println("3. 已到达：" + phaser.getArrivedParties());
		System.out.println("4. 未到达：" + phaser.getUnarrivedParties());
	}
}
