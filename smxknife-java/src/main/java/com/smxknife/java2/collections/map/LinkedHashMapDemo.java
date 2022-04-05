package com.smxknife.java2.collections.map;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author smxknife
 * 2020/7/6
 */
public class LinkedHashMapDemo {
	static HashMap<Integer, Integer> hashMap = new HashMap<>();
	static LinkedHashMap<Integer, Integer> linkedHashMap = new LinkedHashMap(10, 0.75f, true);
	public static void main(String[] args) {
		put(10, 10);
		put(2, 2);
		put(20, 20);

		output();

		getOrder();
	}

	private static void getOrder() {
		System.out.println("为了测试访问顺序，这里LinkedHashMap开启了访问顺序功能，即在构造函数里true，默认是不开启");
		System.out.println("原顺序：" + linkedHashMap);
		linkedHashMap.get(20);
		System.out.println("linked get 20, " + linkedHashMap);
		linkedHashMap.get(2);
		System.out.println("linked get 2, " + linkedHashMap);
		linkedHashMap.get(10);
		System.out.println("linked get 10, " + linkedHashMap);
		System.out.println("通过上面访问之后的输出可以看出，如果开启了访问顺序后，访问了谁就把谁放在最后");
	}

	public static void put(Integer key, Integer val) {
		hashMap.put(key, val);
		linkedHashMap.put(key, val);
	}

	public static void output() {
		System.out.println("hashMap = " + hashMap);
		System.out.println("linkedHashMap = " + linkedHashMap);
	}
}
