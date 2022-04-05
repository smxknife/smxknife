package com.smxknife.java2.thread.executorservice.demo08;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/8/21
 */
public class _RunShutdownDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		executorService.submit(() -> {
			try {
				TimeUnit.SECONDS.sleep(60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		executorService.shutdown();
		System.out.println(executorService.isShutdown());
		System.out.println(executorService.isTerminated());
		executorService.shutdownNow();
		System.out.println(executorService.isShutdown());
		System.out.println(executorService.isTerminated());
	}
}
