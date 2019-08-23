package com.smxknife.java2.unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019-08-22
 */
public class UnsafeLockDemo {
	public static void main(String[] args) {
		int num = 1000;
		singleThread(num);
		multiThreadNoLock(num);
		multiThreadWithLock(num);
	}

	private static void multiThreadWithLock(int num) {
		class Int {
			int sum = 0;
			public void increment() {
				sum++;
			}
		}
		Int sum = new Int();

		UnsafeLock lock = new UnsafeLock();
		ExecutorService service = Executors.newFixedThreadPool(3);
		List<CompletableFuture<Void>> futures = Stream.iterate(0, i -> i + 1).limit(num)
				.map(idx -> CompletableFuture.runAsync(() -> {
					try {
						lock.lock();
//						System.out.println("No. " + idx + " - " + Thread.currentThread().getName());
						sum.increment();
					} finally {
						lock.unlock();
					}
				}, service))
				.collect(Collectors.toList());
		CompletableFuture[] toArray = futures.toArray(new CompletableFuture[num]);
		CompletableFuture.allOf(toArray).join();

		service.shutdown();
		System.out.println("======= multiThreadWithLock sum = " + sum.sum);
	}

	private static void multiThreadNoLock(int num) {
		class Int {
			int sum = 0;
			public void increment() {
				sum++;
			}
		}
		Int sum = new Int();
		List<Runnable> tasks = new ArrayList<>(num);
		for (int i = 0; i < num; i++) {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					sum.increment();
				}
			};
			tasks.add(runnable);
		}

		ExecutorService service = Executors.newFixedThreadPool(3);
		List<CompletableFuture<Void>> futures = tasks.stream().map(r -> CompletableFuture.runAsync(r, service)).collect(Collectors.toList());
		CompletableFuture[] toArray = futures.toArray(new CompletableFuture[num]);
		CompletableFuture.allOf(toArray).join();

		service.shutdown();
		System.out.println("======= multiThreadNoLock sum = " + sum.sum);

	}

	private static void singleThread(int num) {
		int sum = 0;
		for (int i = 0; i < num; i++) {
			sum++;
		}
		System.out.println("======= singleThread sum = " + sum);
	}
}
