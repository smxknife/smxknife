package com.smxknife.java2.thread.executorservice.demo01;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/8/28
 */
public class _Run_invokeAny {
	public static void main(String[] args) {
		List<MyCallable> callables = Stream.iterate(0, i -> i + 1).limit(10)
				.map(idx -> new MyCallable(new UserInfo()))
				.collect(Collectors.toList());

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		try {
			UserInfo userInfo = executorService.invokeAny(callables);
			System.out.println("invokeAny : " + userInfo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executorService.shutdown();
	}
}
