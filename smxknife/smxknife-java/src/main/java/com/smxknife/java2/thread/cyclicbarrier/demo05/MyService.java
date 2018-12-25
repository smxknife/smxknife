package com.smxknife.java2.thread.cyclicbarrier.demo05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author smxknife
 * 2018-12-25
 */
public class MyService {
	private CyclicBarrier barrier;

	public MyService(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	private void beginRun(int count) {
		System.out.println(Thread.currentThread().getName() + " 到了，等待中...");
		try {
			if ("runner-2".equals(Thread.currentThread().getName())) {
				Thread.sleep(5000);
//				Thread.currentThread().interrupt();
			}
			barrier.await(3, TimeUnit.SECONDS);
			System.out.println("都到了，开始跑");
			System.out.println(Thread.currentThread().getName() + " 到达终点，并结束第 " + count + " 赛段");
		} catch (InterruptedException e) {
			System.out.println(Thread.currentThread().getName() + " 进入了InterruptedException " + barrier.isBroken());
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			System.out.println(Thread.currentThread().getName() + " 进入了BrokenBarrierException " + barrier.isBroken());
			e.printStackTrace();
		} catch (TimeoutException e) {
			System.out.println(Thread.currentThread().getName() + " 进入了TimeoutException " + barrier.isBroken());
			e.printStackTrace();
		}
	}

	public void testA() {
		for (int i = 0; i < 1; i++) {
			beginRun(i + 1);
		}
	}
}
