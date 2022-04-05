package com.smxknife.java2.thread.cyclicbarrier.demo01;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyThread extends Thread {

	private CyclicBarrier barrier;
	private Random mock = new Random();

	public MyThread(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		int random = mock.ints(1, 10).unordered().findAny().getAsInt();
		try {
			Thread.sleep(random * 1000);
			System.out.println(Thread.currentThread().getName() + " 到了 " + System.currentTimeMillis());
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
