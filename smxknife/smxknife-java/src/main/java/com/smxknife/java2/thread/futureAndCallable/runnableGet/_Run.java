package com.smxknife.java2.thread.futureAndCallable.runnableGet;

import java.time.LocalDateTime;
import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019-08-19
 */
public class _Run {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		UserInfo userInfo = new UserInfo("name", "pwd");
		RunnableGet runnable = new RunnableGet(userInfo);

		ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 10, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		Future<UserInfo> future = executor.submit(runnable, userInfo);
		System.out.println("A: " + LocalDateTime.now());
		System.out.println(userInfo);
		System.out.println("B: " + LocalDateTime.now());
		userInfo = future.get();
		System.out.println("C: " + LocalDateTime.now());
		System.out.println(userInfo);
		System.out.println("D: " + LocalDateTime.now());

		executor.shutdown();
	}
}
