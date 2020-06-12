package com.smxknife.threadpattern.future;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2020/4/6
 */
public class _Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

		FutureTask<String> futureTask = new FutureTask<>(new RealData("test"));
		ExecutorService service = Executors.newSingleThreadExecutor();
		service.submit(futureTask);
		System.out.println("发送请求完毕");

		String s = futureTask.get(6, TimeUnit.SECONDS);
		System.out.println(s);
	}
}
