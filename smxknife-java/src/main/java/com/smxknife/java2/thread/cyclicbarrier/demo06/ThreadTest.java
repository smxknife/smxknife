package com.smxknife.java2.thread.cyclicbarrier.demo06;

import lombok.SneakyThrows;

import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2020/6/5
 */
public class ThreadTest extends Thread {

	private CyclicBarrier cyclicBarrier;

	public ThreadTest(CyclicBarrier cyclicBarrier) {
		this.cyclicBarrier = cyclicBarrier;
	}

	@SneakyThrows
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " arrive");
		cyclicBarrier.await();
		System.out.println(Thread.currentThread().getName() + " go on");
	}
}
