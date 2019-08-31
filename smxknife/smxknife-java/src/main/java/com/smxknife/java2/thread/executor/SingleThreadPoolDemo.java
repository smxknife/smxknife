package com.smxknife.java2.thread.executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smxknife
 * 2019/8/24
 */
public class SingleThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		// 运行报错，因为newSingleThreadExecutor返回的不是ThreadPoolExecutor，而是FinalizableDelegatedExecutorService
		// 该方法提供了另一个shutdown方法，finalize，同样是调用shutdown
//		System.out.println(((ThreadPoolExecutor)executorService).getCorePoolSize());
//		System.out.println(((ThreadPoolExecutor)executorService).getMaximumPoolSize());

	}
}
