package com.smxknife.java2.thread.cyclicbarrier.demo02;

import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2018-12-25
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		CyclicBarrier barrier = new CyclicBarrier(3);

		MyThread myThreadA = new MyThread(barrier);
		myThreadA.setName("thread-a");
		myThreadA.start();

		Thread.sleep(2000);
		System.out.println(barrier.getNumberWaiting());

		MyThread myThreadB = new MyThread(barrier);
		myThreadB.setName("thread-b");
		myThreadB.start();

		Thread.sleep(2000);
		System.out.println(barrier.getNumberWaiting());

		MyThread myThreadC = new MyThread(barrier);
		myThreadC.setName("thread-c");
		myThreadC.start();

		Thread.sleep(2000);
		System.out.println(barrier.getNumberWaiting());



	}
}
