package com.smxknife.java2.shutdownhook;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/7/31
 */
public class _07_ShutdownHook_MultiThreadSafety_Demo {

	private static Counter counter = new Counter();
	public static void main(String[] args) {

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_1"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_2"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_3"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_4"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_5"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_6"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_7"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_8"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_9"));

		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			handle();
		}, "hook_10"));

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.submit(() -> {
			System.out.println("模拟应用正在执行");
		});
	}

	static class Counter {
		int count = 0;
	}

	private static synchronized void handle() {
		//System.out.println(Thread.currentThread().getName() + " start handle");
		int count = counter.count;
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		counter.count++;
		System.out.println(Thread.currentThread().getName() + " org count = " + count + " after count = " + counter.count);
		//System.out.println(Thread.currentThread().getName() + " end handle");
	}
}
