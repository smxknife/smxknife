package com.smxknife.java2.thread.futureAndCallable.executeAndSubmit;

import java.util.concurrent.*;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run_ReturnValue {
	public static void main(String[] args) throws ExecutionException, InterruptedException {

		ExecutorService service = Executors.newFixedThreadPool(3);

		service.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println("execute running, 没有返回值");
			}
		});

		Future<String> future = service.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("submit running, 有返回值");
				return "返回值";
			}
		});
		System.out.println(future.get());
		service.shutdown();
	}
}
