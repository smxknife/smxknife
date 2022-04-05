package com.smxknife.java2.thread.executorservice.demo02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/8/29
 */
public class _Run_RuntimeException {
	public static void main(String[] args) {

		// invokeAny单个异常时，获取争取的那个callable

		Callable<String> callableRuntimeExceptionWithNothrow = new CallableRuntimeExceptionWithNothrow();
		Callable<String> normalCallable = new CallableNormal();

		List list = new ArrayList<>();
		list.add(callableRuntimeExceptionWithNothrow);
		list.add(normalCallable);

		ExecutorService executorService = Executors.newCachedThreadPool();

		try {
			String t = (String) executorService.invokeAny(list);
			System.out.println("result = " + t);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}
}
