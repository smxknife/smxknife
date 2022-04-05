package com.smxknife.java2.thread.executorservice.demo06;

import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _Run_submit_exception_finally {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		Future<Object> future = executorService.submit(() -> {
			try {
				throw new SocketException();
			} finally {
				System.out.println("xxxxxxxxx");
			}
		});
	}
}
