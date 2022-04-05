package com.smxknife.java2.shutdownhook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _06_ShutdownHook_WhileThread_Demo {
	public static void main(String[] args) {
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			while (true) {
				try {
					System.out.println("xxxxx");
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}));

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.submit(() -> {
			System.out.println("模拟应用正在执行");
		});
	}
}
