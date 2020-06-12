package com.smxknife.java2.thread.cyclicbarrier.demo06;

import com.smxknife.java2.thread.cyclicbarrier.demo01.MyThread;

import java.util.concurrent.CyclicBarrier;

/**
 * @author smxknife
 * 2020/6/5
 */
public class Main {
	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(4, () -> {
			System.out.println("全部到位");
		});

		int num = 8;
		ThreadTest[] threads = new ThreadTest[num];
		for (int i = 0; i < num; i++) {
			threads[i] = new ThreadTest(cyclicBarrier);
			threads[i].setName("runner-" + (i + 1));
			threads[i].start();
		}
	}
}
