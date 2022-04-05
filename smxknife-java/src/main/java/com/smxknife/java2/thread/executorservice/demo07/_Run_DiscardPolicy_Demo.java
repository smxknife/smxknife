package com.smxknife.java2.thread.executorservice.demo07;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/8/3
 */
public class _Run_DiscardPolicy_Demo {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS,
				new SynchronousQueue<>(), new ThreadPoolExecutor.DiscardPolicy());

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

		executor.execute(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " llll");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		executor.execute(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " mmmm");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		executor.execute(() -> {
			while (true) {
				System.out.println(Thread.currentThread().getName() + " nnnn");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

	}
}
