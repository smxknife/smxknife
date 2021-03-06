package com.smxknife.java2.thread.futureAndCallable.exception;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author smxknife
 * 2019-08-22
 */
public class _Run {
	public static void main(String[] args) {
		ExceptionCallable callable = new ExceptionCallable();

		ExecutorService service = Executors.newFixedThreadPool(3);
		Future<String> future = service.submit(callable);

		try {
			future.get();
		} catch (Exception e) {
			System.out.println("Exception");
		}

//		try {
//			System.out.println(future.get());
//		} catch (InterruptedException e) {
//			System.out.println("catch interrupted");
//			e.printStackTrace();
//		} catch (ExecutionException e) {
//			System.out.println("catch execution");
//			e.printStackTrace();
//		}
	}
}
