package com.smxknife.java2.thread.executorservice.demo07;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2020/8/3
 */
public class _Run_AbortPolicy_Demo {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 0, TimeUnit.SECONDS,
				new SynchronousQueue<>(), new ThreadPoolExecutor.AbortPolicy());

		executor.submit(() -> {
			while (true) {
				System.out.println("xxxx");
				TimeUnit.SECONDS.sleep(1);
			}
		});

		executor.submit(() -> {
			while (true) {
				System.out.println("yyyy");
				TimeUnit.SECONDS.sleep(1);
			}

		});

		executor.submit(() -> {
			while (true) {
				System.out.println("zzzz");
				TimeUnit.SECONDS.sleep(1);
			}
		});

//		executor.shutdown();

		executor.submit(() -> {
			System.out.println("llll");
		});

	}
}
