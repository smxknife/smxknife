package com.smxknife.java.ex31;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2020/4/14
 */
public class CCopy {
	public static void main(String[] args) {
		Map<String, List<Integer>> org = new ConcurrentHashMap<>();
		org.put("a", new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
		org.put("b", new ArrayList<>(Arrays.asList(10, 20, 30, 40)));
		System.out.println(org);
		Map<String, List<Integer>> dest = new ConcurrentHashMap<>();
		org.entrySet().forEach(entry -> {
			dest.put(entry.getKey(), new ArrayList<>(entry.getValue()));
			entry.getValue().clear();
		});
		System.out.println(org);
		System.out.println(dest);
	}
}
