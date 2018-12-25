package com.smxknife.java2.thread.cyclicbarrier.demo02;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyThread extends Thread {

	private CyclicBarrier barrier;

	public MyThread(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			barrier.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
}
