package com.smxknife.java2.thread.futureAndCallable.getTimeout;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run {
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

		GetTimeoutCallable callable = new GetTimeoutCallable("smxknife");

		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 3, 5L, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
		System.out.println("begin " + System.currentTimeMillis());
		Future<String> future = poolExecutor.submit(callable);
		System.out.println(future.get(5, TimeUnit.SECONDS));
		poolExecutor.shutdown();
	}
}
