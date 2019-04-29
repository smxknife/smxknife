package com.smxknife.java.ex25;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019-04-22
 */
public class ThreadPoolExecutorDemo {
	public static void main(String[] args) {
		// FIXME shao
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 10, TimeUnit.SECONDS, null);
	}
}
