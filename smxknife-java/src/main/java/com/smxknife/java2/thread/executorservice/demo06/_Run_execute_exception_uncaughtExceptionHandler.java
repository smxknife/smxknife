package com.smxknife.java2.thread.executorservice.demo06;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _Run_execute_exception_uncaughtExceptionHandler {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3, new ThreadFactoryBuilder().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(t.getName());
				System.out.println(e.getMessage());
			}
		}).build());

		executorService.execute(() -> {
			throw new RuntimeException();
		});

	}
}
