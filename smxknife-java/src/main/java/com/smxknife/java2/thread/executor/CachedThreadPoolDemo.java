package com.smxknife.java2.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author smxknife
 * 2019/8/23
 */
public class CachedThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println(((ThreadPoolExecutor)service).getCorePoolSize());
		System.out.println(((ThreadPoolExecutor)service).getMaximumPoolSize());
	}
}
