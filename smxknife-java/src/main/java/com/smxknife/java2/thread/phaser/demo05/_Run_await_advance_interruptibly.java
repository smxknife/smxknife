package com.smxknife.java2.thread.phaser.demo05;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/8/26
 */
public class _Run_await_advance_interruptibly {
	public static void main(String[] args) throws InterruptedException {
		Phaser phaser = new Phaser(3);
		Thread myThread = new MyThread_AwaitAdvanceInterruptibly(phaser);
		myThread.setName("A");
		myThread.start();

		TimeUnit.SECONDS.sleep(5);
		myThread.interrupt();
		System.out.println("中断"); // 通过该内容可以输出判断，myThread没有被打断

	}
}
