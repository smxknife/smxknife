package com.smxknife.java2.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/8/21
 */
public class Demo02 {
	public static void main(String[] args) {

		CompletableFuture[] completableFutures = Stream.iterate(0, idx -> idx + 1).limit(10).map(idx -> CompletableFuture.supplyAsync(() -> {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "success";
		}).whenComplete((val, th) -> {
			System.out.println(val);
			System.out.println(th);
		})).toArray(CompletableFuture[]::new);

		CompletableFuture.allOf(completableFutures).runAfterEither(CompletableFuture.runAsync(() -> {
			System.out.println("yyyy");
		}), () -> {
			System.out.println("xxx");
		}).join();


		System.out.println("main");
	}
}
