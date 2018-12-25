package com.smxknife.java2.thread.cyclicbarrier.demo01;

import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2018-12-25
 */
public class Main {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
			System.out.println("全部到位");
		});
		MyThread[] threads = new MyThread[5];
		for (int i = 0; i < 5; i++) {
			threads[i] = new MyThread(cyclicBarrier);
			threads[i].setName("runner-" + i);
			threads[i].start();
		}
	}
}
