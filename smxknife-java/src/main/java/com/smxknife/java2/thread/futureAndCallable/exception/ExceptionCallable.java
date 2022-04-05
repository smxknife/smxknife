package com.smxknife.java2.thread.futureAndCallable.exception;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2019-08-22
 */
public class ExceptionCallable implements Callable<String> {
	@Override
	public String call() throws Exception {
		Integer.parseInt("a");
		return null;
	}
}
