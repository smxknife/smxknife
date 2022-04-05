package com.smxknife.java2.thread.executorservice.demo02;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/8/28
 */
public class _Run {
	public static void main(String[] args) {

		try {
			CallableException callableException = new CallableException();
			CallableNormal callableNormal = new CallableNormal();
			CallableExceptionWithAnotherTryCatch callableExceptionWithAnotherTryCatch = new CallableExceptionWithAnotherTryCatch();

			List list = new ArrayList<>();
			list.add(callableException);
			list.add(callableNormal);
			list.add(callableExceptionWithAnotherTryCatch);

			ExecutorService executorService = Executors.newCachedThreadPool();
			String string = (String) executorService.invokeAny(list);
			System.out.println("result = " + string);
		} catch (Exception e) {
			System.out.println("main exception");
			e.printStackTrace();
		}
	}
}
