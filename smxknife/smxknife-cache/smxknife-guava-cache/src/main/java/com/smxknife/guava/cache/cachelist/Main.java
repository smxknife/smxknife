package com.smxknife.guava.cache.cachelist;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import java.util.Arrays;
import java.util.List;

/**
 * @author smxknife
 * 2020/9/7
 */
public class Main {
	public static void main(String[] args) {
		Cache<String, List<String>> cache = CacheBuilder.newBuilder().maximumSize(100).build();

		cache.put("aaaa", Arrays.asList("111", "222", "333"));
		List<String> aaaa = cache.getIfPresent("aaaa");
		System.out.println(aaaa);

		aaaa.remove(0);

		List<String> bbb = cache.getIfPresent("aaaa");
		System.out.println(bbb);
	}
}
