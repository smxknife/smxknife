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
public class _Run_all_exception {
	public static void main(String[] args) {
		Callable<String> callableRuntimeExceptionWithNothrow = new CallableRuntimeExceptionWithNothrow();
		CallableException callableException = new CallableException();
		CallableExceptionWithAnotherTryCatch callableExceptionWithAnotherTryCatch = new CallableExceptionWithAnotherTryCatch();

		List list = new ArrayList();
		list.add(callableException);
		list.add(callableExceptionWithAnotherTryCatch);
		list.add(callableRuntimeExceptionWithNothrow);

		ExecutorService executorService = Executors.newCachedThreadPool();

		try {
			Object o = executorService.invokeAny(list);
			System.out.println(o);
		} catch (InterruptedException e) {
			System.out.println("interrupt...");
			e.printStackTrace();
		} catch (ExecutionException e) {
			System.out.println("execution...");
			e.printStackTrace();
		}
	}
}
