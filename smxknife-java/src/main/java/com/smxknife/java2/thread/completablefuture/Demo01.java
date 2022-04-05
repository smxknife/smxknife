package com.smxknife.java2.thread.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * @author smxknife
 * 2020/4/22
 */
public class Demo01 {
	public static void main(String[] args) {
		// 创建CompletableFuture的两种方式：这两种方式每种都有指定线程池的方法（相当于四个方法）
		CompletableFuture<String> supplyAsyncCompletableFuture = static_supplyAsync("Hello");
		CompletableFuture<String> supplyAsyncCompletableFuture2 = static_supplyAsync("World");
		CompletableFuture runAsyncCompletableFuture = static_runAsync();

		// thenApply
		thenApply(supplyAsyncCompletableFuture).whenComplete((val, err) -> System.out.println(val));

		// thenAccept
		thenAccept(supplyAsyncCompletableFuture).whenComplete((val, err) -> System.out.println(val));

		// thenRun
		thenRun(supplyAsyncCompletableFuture).whenComplete((val, err) -> System.out.println(val));

		// thenCombine
		thenCombine(supplyAsyncCompletableFuture, supplyAsyncCompletableFuture2).whenComplete((val, err) -> System.out.println(val));

		// thenAcceptBoth
		thenAcceptBoth(supplyAsyncCompletableFuture, supplyAsyncCompletableFuture2).whenComplete((val, err) -> System.out.println(val));

		// thenCompose
		thenCompose(supplyAsyncCompletableFuture, supplyAsyncCompletableFuture2).whenComplete((val, err) -> System.out.println(val));

		// handle
		handle(supplyAsyncCompletableFuture).whenComplete((val, err) -> System.out.println(val));
	}

	private static CompletionStage<String> handle(CompletableFuture<String> supplyAsyncCompletableFuture) {
		return supplyAsyncCompletableFuture.handle((val, th) -> val.toUpperCase());
	}

	private static CompletionStage<String> thenCompose(CompletableFuture<String> supplyAsyncCompletableFuture, CompletableFuture<String> supplyAsyncCompletableFuture2) {
		// thenCompose用来连接两个CompletableFuture，返回值是新的CompletableFuture
		// 这里明显区分thenApply，thenApply返回的是一个值，而thenCompose返回的是一个CompletableFuture
		return supplyAsyncCompletableFuture.thenCompose(str -> supplyAsyncCompletableFuture2);
	}

	private static CompletionStage<Void> thenAcceptBoth(CompletableFuture<String> supplyAsyncCompletableFuture, CompletableFuture<String> supplyAsyncCompletableFuture2) {
		return supplyAsyncCompletableFuture.thenAcceptBoth(supplyAsyncCompletableFuture2, (s1, s2) -> System.out.println("thenAcceptBoth " + s1 + s2));
	}

	private static CompletionStage<String> thenCombine(CompletableFuture<String> supplyAsyncCompletableFuture, CompletableFuture<String> supplyAsyncCompletableFuture2) {
		// thenCombine整合两个计算结果
		return supplyAsyncCompletableFuture.thenCombine(supplyAsyncCompletableFuture2, (s1, s2) -> {
			System.out.println("s1 = " + s1);
			System.out.println("s2 = " + s2);
			return s1 + s2;
		});
	}

	private static CompletionStage<Void> thenRun(CompletableFuture<String> future) {
		// thenRun与thenAccept比较类似，都是没有输出的，但是thenAccept是可以把前阶段的输出，作为输入，而thenRun则是不管前阶段的任何输出
		return future.thenRun(() -> System.out.println("........"));
	}

	private static CompletionStage<Void> thenAccept(CompletableFuture<String> future) {
		// thenAccept是将前阶段的输出进行无法回的消费
		return future.thenAccept((val) -> System.out.println("----" + val));
	}

	private static CompletableFuture<String> thenApply(CompletableFuture<String> future) {
		// thenApply把前阶段的结果当做下一个阶段的输入参数。对应的还有一个thenApplyAsync默认异步执行，这里的异步是指不在当前线程内执行
		return future.thenApply(val -> val.toUpperCase());
	}

	private static CompletableFuture static_runAsync() {
		// runAsync是不能有任何返回的，所以下面这样会编译报错，不能有返回值
		//CompletableFuture.runAsync(() -> "Hello");
		// 像这样，执行void 返回的方法是可以的，有返回的不行
		return CompletableFuture.runAsync(() -> System.out.println("Hello"));
	}

	private static CompletableFuture<String> static_supplyAsync(String str) {
		// supplyAsync是有返回值的
		return CompletableFuture.supplyAsync(() -> str);
	}
}
