package com.smxknife.java2.shutdownhook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _07_ShutdownHook_ExceptionThread_Demo {
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			throw new RuntimeException();
		}));

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.submit(() -> {
			System.out.println("模拟应用正在执行");
		});
	}
}
