package com.smxknife.guava.cache.remove;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/9/7
 */
public class Main {
	public static void main(String[] args) throws InterruptedException {
		Cache<String, String> cache = CacheBuilder.newBuilder()
				.removalListener(new RemovalListener<String, String>() {
					@Override
					public void onRemoval(RemovalNotification<String, String> removalNotification) {
						System.out.println(removalNotification.getKey() + " wasEvicted: " + removalNotification.wasEvicted());
					}
				}).expireAfterWrite(Duration.ofSeconds(2))
				.build();

		cache.put(new String("aaa"), "aaa1");

		System.out.println(cache.getIfPresent("aaa"));
		System.gc();
//		cache.invalidate("aaa");
		TimeUnit.SECONDS.sleep(10);
	}
}
