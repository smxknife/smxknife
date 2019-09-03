package com.smxknife.java2.collections.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author smxknife
 * 2019/9/1
 */
public class HashSetDemo {
	public static void main(String[] args) {

		// HashSet的底层是一个HashMap作为存储
		Set<String> sets = new HashSet<>();

		// 每次add一个对象后，底层的map是把对象作为key，一个类共享的Object作为虚值存储在value中
		// private static final Object PRESENT = new Object(); 就是这个东西，map.put("a", PRESENT);
		sets.add("a");

		// hashset底层采用的hashmap所以，相关操作就是以hashmap的key基本操作封装
	}
}
