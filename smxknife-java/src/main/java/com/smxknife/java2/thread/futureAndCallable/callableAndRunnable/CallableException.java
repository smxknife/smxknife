package com.smxknife.java2.thread.futureAndCallable.callableAndRunnable;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2019-08-19
 */
public class CallableException implements Callable {
	@Override
	public Object call() throws Exception {
		throw new Exception("");
	}
}
