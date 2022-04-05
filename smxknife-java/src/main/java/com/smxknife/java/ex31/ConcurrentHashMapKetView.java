package com.smxknife.java.ex31;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2020/7/22
 */
public class ConcurrentHashMapKetView {
	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Set<Integer>> map = new ConcurrentHashMap<>();

		map.keySet(new HashSet<>()).addAll(Arrays.asList(1, 3, 5));
		map.entrySet().forEach(entry -> {
			System.out.println(System.identityHashCode(entry.getValue()));
		});
	}
}
