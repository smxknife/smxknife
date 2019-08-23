package com.smxknife.java2.thread.futureAndCallable.executeAndSubmit;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run_SubmitException {
	public static void main(String[] args) {

		ExecutorService service = Executors.newFixedThreadPool(3);
		try {
			Future<String> future = service.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.out.println("submit running...");
					Integer.parseInt("a");
					return "返回值";
				}
			});
			future.get();
		} catch (Exception e) {
			// TODO: ATTENTION 这里只有调用get方法之后才会捕获到异常，否则无法catch是捕获不到任何异常
			System.out.println("异常捕获到了：" + e.getMessage());
		}

		service.shutdown();
	}
}
