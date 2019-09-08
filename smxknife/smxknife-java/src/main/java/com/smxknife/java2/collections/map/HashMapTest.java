package com.smxknife.java2.collections.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019/9/4
 */
public class HashMapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("k1", "v1");
		System.out.println(map);
		map.put("k1", "vv1");
		System.out.println(map);

		map.put(new String("k1"), "vvv1");
		System.out.println(map);
	}
}
