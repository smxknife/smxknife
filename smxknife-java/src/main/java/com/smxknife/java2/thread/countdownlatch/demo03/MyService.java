package com.smxknife.java2.thread.countdownlatch.demo03;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyService {

	private CountDownLatch down = new CountDownLatch(1);
	private Random mock = new Random();

	public void prepare() {
		try {
			System.out.println(Thread.currentThread().getName() + " 准备好了");
			down.await();
			execute();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void execute() {
		System.out.println(Thread.currentThread().getName() + " executing!");
		int rank = mock.ints(1, 15).findAny().getAsInt();
		try {
			Thread.sleep(rank * 1000);
			System.out.println(Thread.currentThread().getName() + " finished in " + rank + " seconds!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cmdStart() {
		System.out.println(Thread.currentThread().getName() + " 开始");
		down.countDown();
	}
}
