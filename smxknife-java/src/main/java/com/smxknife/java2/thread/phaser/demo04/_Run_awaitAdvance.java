package com.smxknife.java2.thread.phaser.demo04;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2019/8/26
 */
public class _Run_awaitAdvance {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(3);

		ThreadA threadA = new ThreadA(phaser);
		threadA.setName("A");
		threadA.start();

		ThreadB threadB = new ThreadB(phaser);
		threadB.setName("B");
		threadB.start();

		ThreadC_AwaitAdvance threadC = new ThreadC_AwaitAdvance(phaser);
		threadC.setName("C");
		threadC.start();

		ThreadD threadD = new ThreadD(phaser);
		threadD.setName("D");
		threadD.start();
	}
}
