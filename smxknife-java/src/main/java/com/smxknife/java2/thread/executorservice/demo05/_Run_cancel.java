package com.smxknife.java2.thread.executorservice.demo05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/28
 */
public class _Run_cancel {
	public static void main(String[] args) throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);

		Future<Object> future = executorService.submit(() -> {
			while (true) {
				System.out.println("xxxx");
				TimeUnit.SECONDS.sleep(1);
			}
		});

		TimeUnit.SECONDS.sleep(3);
		boolean cancel = future.cancel(true);
		System.out.println("cancel " + cancel);
		System.out.println("future.isCancel " + future.isCancelled());

	}
}
