package com.smxknife.java2.thread.executorservice.demo06;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _Run_WithExceptionThreadPoolExecutor_submit {
	public static void main(String[] args) {
		WithExceptionThreadPoolExecutor executor = new WithExceptionThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

		executor.submit(() -> {
			throw new RuntimeException();
		});
	}
}
