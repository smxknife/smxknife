package com.smxknife.java2.thread.executorservice.demo07;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/8/3
 */
public class _Run_DiscardOldestPolicy_Demo {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS,
				new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardOldestPolicy());

		executor.submit(() -> {
			System.out.println(Thread.currentThread().getName() + " xxxx");
		});

		executor.submit(() -> {
			System.out.println(Thread.currentThread().getName() + " yyyy");
		});

		executor.submit(() -> {
			System.out.println(Thread.currentThread().getName() + " zzzz");
		});

		executor.execute(() -> {
			System.out.println(Thread.currentThread().getName() + " llll");
		});

		executor.execute(() -> {
			System.out.println(Thread.currentThread().getName() + " mmmm");
		});

		executor.execute(() -> {
			System.out.println(Thread.currentThread().getName() + " nnnn");
		});
	}
}
