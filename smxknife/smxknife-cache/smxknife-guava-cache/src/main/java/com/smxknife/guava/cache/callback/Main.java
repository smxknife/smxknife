package com.smxknife.guava.cache.callback;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/6/13
 */
public class Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		Cache<Object, Object> cache = CacheBuilder.newBuilder().maximumSize(100).build();

		List<Thread> threads = IntStream.iterate(0, idx -> idx + 1).limit(20).mapToObj(idx -> new Thread(() -> {
			try {
				Object val = cache.get("test", new Callable<Object>() {
					@Override
					public Object call() throws Exception {
						System.out.println(Thread.currentThread().getName() + " call");
						return "hello";
					}
				});
				System.out.println(Thread.currentThread().getName() + " get key=test, value= " + val);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}, "th-" + idx)).collect(Collectors.toList());

		threads.forEach(Thread::start);

		TimeUnit.SECONDS.sleep(5);

		System.out.println("cache status: " + cache.stats());
	}
}
