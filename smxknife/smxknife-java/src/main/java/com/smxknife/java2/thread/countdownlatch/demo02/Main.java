package com.smxknife.java2.thread.countdownlatch.demo02;

import java.util.concurrent.CountDownLatch;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018-12-25
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch maxRunner = new CountDownLatch(10);
		MyThread[] threads = new MyThread[10];
		Stream.iterate(0, idx -> idx + 1).limit(10).forEach(idx -> {
			threads[idx] = new MyThread(maxRunner);
			threads[idx].setName("runner-" + idx);
			threads[idx].start();
		});
//		System.out.println(maxRunner.getCount());
		maxRunner.await();
//		System.out.println(maxRunner.getCount());
		System.out.println("all back | count = " + maxRunner.getCount());
	}
}
