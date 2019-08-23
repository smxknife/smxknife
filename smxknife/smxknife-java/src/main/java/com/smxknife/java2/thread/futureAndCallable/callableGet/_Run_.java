package com.smxknife.java2.thread.futureAndCallable.callableGet;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019-08-19
 */
public class _Run_ {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		CallableGetMethod callable = new CallableGetMethod(10);

		ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 8,
				5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		Future<String> future = executor.submit(callable);
		System.out.println("A: " + LocalDateTime.now());
		System.out.println(future.get());
		System.out.println("B: " + LocalDateTime.now());
		System.out.println(future.get());
		System.out.println("C: " + LocalDateTime.now());
		System.out.println("isDone: " + future.isDone());
		executor.shutdown();
		System.out.println(future.get());
		System.out.println("D: " + LocalDateTime.now());
		System.out.println("isDone: " + future.isDone());

	}
}
