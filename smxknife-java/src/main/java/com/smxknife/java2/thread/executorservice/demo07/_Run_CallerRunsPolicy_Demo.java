package com.smxknife.java2.thread.executorservice.demo07;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/8/3
 */
public class _Run_CallerRunsPolicy_Demo {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS,
				new SynchronousQueue<>(), new ThreadPoolExecutor.CallerRunsPolicy());

		executor.submit(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " xxxx");
				TimeUnit.SECONDS.sleep(10);
			}
		});

		executor.submit(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " yyyy");
				TimeUnit.SECONDS.sleep(10);
			}

		});

		executor.submit(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " zzzz");
				TimeUnit.SECONDS.sleep(10);
			}
		});

		executor.submit(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " llll");
				TimeUnit.SECONDS.sleep(1);
			}
		});

		executor.submit(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " mmmm");
				TimeUnit.SECONDS.sleep(1);
			}
		});

		executor.submit(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " nnnn");
				TimeUnit.SECONDS.sleep(1);
			}
		});

	}
}
