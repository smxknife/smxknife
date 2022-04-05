package com.smxknife.java2.thread.executorservice.demo06;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _Run_execute_exception {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);

		executorService.execute(() -> {
			throw new RuntimeException();
		});

	}
}
