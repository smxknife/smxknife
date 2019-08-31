package com.smxknife.java2.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author smxknife
 * 2019/8/24
 */
public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		System.out.println(((ThreadPoolExecutor)executorService).getCorePoolSize());
		System.out.println(((ThreadPoolExecutor)executorService).getMaximumPoolSize());
	}
}
