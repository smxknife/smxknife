package com.smxknife.java2.thread.phaser.demo01;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2019/8/26
 */
public class _Run {
	public static void main(String[] args) {
//		Phaser phaser = new Phaser(3);

		Phaser phaser = new Phaser(3) {

			@Override
			protected boolean onAdvance(int phase, int registeredParties) {
				System.out.println("phase = " + phase + " registeredParties = " + registeredParties);

				return false;
				// return super.onAdvance(phase, registeredParties);
			}

			@Override
			public int register() {
				System.out.println("register");
				return super.register();
			}

			@Override
			public int arrive() {
				System.out.println(Thread.currentThread().getName() + " arrive");
				return super.arrive();
			}
		};

		System.out.println("registeredParties: " + phaser.getRegisteredParties());

		PrintTools.phaser = phaser;

		Thread threadA = new ThreadA(phaser);
		threadA.setName("A");
		threadA.start();

		Thread threadB = new ThreadB(phaser);
		threadB.setName("B");
		threadB.start();

		Thread threadC = new ThreadC(phaser);
		threadC.setName("C");
		threadC.start();

		System.out.println(" current phaser = " + phaser.getPhase());
//		System.out.println(phaser.arrive());

	}
}
