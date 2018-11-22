package com.smxknife.java2.thread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2018/11/21
 */
public class CompletableFutureDemo {
	public static void main(String[] args) throws InterruptedException {
//		ExecutorService service = Executors.newFixedThreadPool(1);
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();//
		CompletableFuture[] futures = Stream.iterate(1, n -> n + 1).limit(10).map(item -> {
			CompletableFuture<Void> future = CompletableFuture.runAsync(new Runner(item), service);
			return future;
		}).toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(futures).join();
		service.shutdown();
	}
}

class Runner implements Runnable {

	private int i;

	public Runner(int i) {
		this.i = i;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
			System.out.println("no: " + i + "---" + Thread.currentThread().getName());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
