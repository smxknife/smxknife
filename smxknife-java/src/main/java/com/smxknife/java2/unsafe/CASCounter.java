package com.smxknife.java2.unsafe;

import sun.misc.Unsafe;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2019-08-21
 */
public class CASCounter {

	private volatile long counter = 0;
	private Unsafe unsafe;
	private long offset;

	public CASCounter(Unsafe unsafe) {
		this.unsafe = unsafe;
		try {
			offset = unsafe.objectFieldOffset(this.getClass().getDeclaredField("counter"));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public long increment() {
		long before = counter;
		System.out.println(Thread.currentThread().getName() + " | enter increment | counter = " + counter);
		while (!unsafe.compareAndSwapLong(this, offset, before, before + 1)) {
			System.out.println(Thread.currentThread().getName() + " | counter = " + counter);
			before = counter;
		}
		System.out.println(Thread.currentThread().getName() + " | before = " + before + " | counter = " + counter);
		return counter;
	}

	public long getCounter() {
		return counter;
	}

	public static void main(String[] args) throws InterruptedException {
		CASCounter casCounter = new CASCounter(UnsafeWrapper.unsafe());
		ExecutorService executorService = Executors.newFixedThreadPool(3);
		List<Callable<Long>> callableList = IntStream.iterate(0, idx -> idx + 1).limit(3).mapToObj(idx -> new Callable<Long>() {
			@Override
			public Long call() throws Exception {
				return casCounter.increment();
			}
		}).collect(Collectors.toList());
		executorService.invokeAll(callableList);
		executorService.shutdown();
	}

}
