package com.smxknife.java.ex20;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2018-12-03
 */
public class ParallelDemo2 {
	public static void main(String[] args) {
		ExecutorService service2 = Executors.newFixedThreadPool(10);
		ExecutorService service = Executors.newFixedThreadPool(1);
		CompletableFuture[] completableFutures = Arrays.asList(1, 2, 3, 4, 5).stream().map(i -> CompletableFuture.supplyAsync(() -> {
			System.out.println(">>>>> " + i);
			return i;
		}, service).thenApply(ii -> {
			CompletableFuture[] futures = Arrays.asList(100, 200, 300, 400).stream().map(jj -> CompletableFuture.supplyAsync(() -> {
				System.out.println("current i = " + ii + ", current value = " + jj + "Thread : " + Thread.currentThread().getName());
				return jj;
			})).toArray(CompletableFuture[]::new);
			CompletableFuture.allOf(futures).join();
//			Arrays.asList(100, 200, 300, 400).stream().parallel().forEach(jj -> {
//				System.out.println("current i = " + ii + ", current value = " + jj + "Thread : " + Thread.currentThread().getName());
//			});
			return ii;
		})).toArray(CompletableFuture[]::new);
		CompletableFuture.allOf(completableFutures).join();
		System.out.println("finish");
	}
}
