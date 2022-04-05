package com.smxknife.java2.thread.executorservice.demo02;

import java.util.concurrent.Callable;

/**
 * @author smxknife
 * 2019/8/29
 */
public class CallableRuntimeExceptionWithNothrow implements Callable<String> {
	@Override
	public String call() throws Exception {
		try {
			throw new NullPointerException("test exception");
		} catch (Exception e) {
			System.out.println("eeeee");
			throw e; // 注意这里如果不抛出e，那么invokeAny很有可能会获取该call的返回值，因为调用线程里不知道该call是异常运行，所以抛出e之后，就知道该线程是异常的，不会获取
		}

//		return "RuntimeException";
	}
}
