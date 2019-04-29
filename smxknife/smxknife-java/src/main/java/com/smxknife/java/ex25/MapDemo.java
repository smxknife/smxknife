package com.smxknife.java.ex25;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019-04-22
 */
public class MapDemo {
	public static void main(String[] args) {
		Map<String, String> test = new HashMap<>();
		test.put("aaa", "10");
		test.put("bbb", "10");
		test.put("ccc", "10");
		test.put("ddd", "10");

		test.forEach((key, val) -> System.out.printf("key=%s, val=%s", key, val));
	}
}
