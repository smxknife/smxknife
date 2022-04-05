package com.smxknife.guava.cache.callbackwithnull;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

/**
 * @author smxknife
 * 2020/9/7
 */
public class Main {
	public static void main(String[] args) throws ExecutionException {
		Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(100).build();
		String aaa = cache.get("aaa", new Callable<String>() {
			@Override
			public String call() throws Exception {
				return null;
			}
		});
		System.out.println(aaa);

	}
}
