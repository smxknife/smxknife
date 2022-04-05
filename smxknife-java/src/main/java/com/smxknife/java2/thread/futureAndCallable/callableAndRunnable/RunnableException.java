package com.smxknife.java2.thread.futureAndCallable.callableAndRunnable;

/**
 * @author smxknife
 * 2019-08-19
 */
public class RunnableException implements Runnable {
	@Override
	public void run() {//throws Exception {
		// TODO 这里编译错误，
		// throw new Exception("");
	}
}
