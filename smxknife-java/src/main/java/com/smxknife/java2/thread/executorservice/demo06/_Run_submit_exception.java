package com.smxknife.java2.thread.executorservice.demo06;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _Run_submit_exception {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Future<Object> future = executorService.submit(() -> {
			throw new RuntimeException();
		});

		try {
			future.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("....ExecutionException");
			e.printStackTrace();
		}
	}
}
