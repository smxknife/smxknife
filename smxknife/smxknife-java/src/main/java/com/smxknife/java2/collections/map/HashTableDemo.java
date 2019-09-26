package com.smxknife.java2.collections.map;

import java.util.Hashtable;
import java.util.Iterator;

/**
 * @author smxknife
 * 2019/9/9
 */
public class HashTableDemo {
	public static void main(String[] args) {
		Hashtable<String, String> hashtable = new Hashtable<>();
		// hashtable加了synchronized关键字

		// hashtable不支持在循环中remove元素
		hashtable.put("k1", "v1");
		hashtable.put("k2", "v2");
		hashtable.put("k3", "v3");
		hashtable.put("k4", "v4");

		Iterator<String> iterator = hashtable.keySet().iterator();
		while (iterator.hasNext()) {
			hashtable.remove(iterator.next());
		}
	}
}
