package com.smxknife.java2.thread.cyclicbarrier.demo05;

import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2018-12-25
 */
public class Main {
	public static void main(String[] args) {
		int parties = 4;
		int threadsNum = 4; // 线程数与parties一致，会继续等待
//		int threadsNum = 5; // 只有异常停止，其他线程依然执行
		CyclicBarrier barrier = new CyclicBarrier(parties, () -> {
			System.out.println("都到了..");
		});

		MyService myService = new MyService(barrier);
		MyThread[] threads = new MyThread[threadsNum];
		for (int i = 0; i < threadsNum; i++) {
			threads[i] = new MyThread(myService);
			threads[i].setName("runner-" + i);
			threads[i].start();
		}
	}
}
