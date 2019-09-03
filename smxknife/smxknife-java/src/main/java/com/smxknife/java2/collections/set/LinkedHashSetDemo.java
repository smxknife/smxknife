package com.smxknife.java2.collections.set;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author smxknife
 * 2019/9/1
 */
public class LinkedHashSetDemo {
	public static void main(String[] args) {
		// LinkedHashSet 更简单，继承HashSet
		Set<String> sets = new LinkedHashSet<>();

		// 那么LinkedHashSet是如何保证顺序的呢？
		// 在HashSet中，存在一个构造方法，但是是包访问权限，HashSet没有暴露出来，如下
		/**
		 * HashSet(int initialCapacity, float loadFactor, boolean dummy) {
		 *         map = new LinkedHashMap<>(initialCapacity, loadFactor);
		 *     }
		 */
		// 将map转为了LinkedHashMap进行存储，保证了有序性

		// 从源码中看，LinkedHashSet除了有序性，其余的与HashSet基本无区别
	}
}
