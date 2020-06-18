package com.smxknife.guava.cache.cacheloader;

import com.google.common.cache.*;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author smxknife
 * 2020/6/13
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		LoadingCache<Integer, String> loadingCache = CacheBuilder.newBuilder()
				// 设置并发级别为8，并发级别是指可以同时写缓存的线程数
				.concurrencyLevel(8)
				// 设置写缓存后8秒钟过期
				.expireAfterWrite(8, TimeUnit.SECONDS)
				// 设置缓存的初始量为10
				.initialCapacity(10)
				// 设置缓存最大容量为100，超过100之后就会按照LRU最近最少使用算法来移除缓存
				.maximumSize(100)
				// 设置要统计缓存的命中率
				.recordStats()
				// 设置缓存的移除通知
				.removalListener(new RemovalListener<Object, Object>() {
					@Override
					public void onRemoval(RemovalNotification<Object, Object> removalNotification) {
						System.out.println(removalNotification.getKey() + " was removed caused is " + removalNotification.getCause());
					}
				})
				.build(
						// build指定CacheLoader，在缓存不存在时通过CacheLoader实现自动加载
						new CacheLoader<Integer, String>() {
							@Override
							public String load(Integer key) throws Exception {
								System.out.println(Thread.currentThread().getName() + " | load data key = " + key);
								return key + ": cache-value";
							}
						}
				);

		List<Thread> threads = IntStream.iterate(0, idx -> idx + 1).limit(20).mapToObj(idx -> new Thread(() -> {
			try {
				String s = loadingCache.get(1);
				System.out.println(Thread.currentThread().getName() + " get key=1, value= " + s);
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}, "th-" + idx)).collect(Collectors.toList());

		threads.forEach(Thread::start);

		TimeUnit.SECONDS.sleep(5);

		System.out.println("cache status: " + loadingCache.stats());
	}
}
