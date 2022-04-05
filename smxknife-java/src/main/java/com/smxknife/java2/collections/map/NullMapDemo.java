package com.smxknife.java2.collections.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2020/7/20
 */
public class NullMapDemo {
	static HashMap hashMap = new HashMap();
	static LinkedHashMap linkedHashMap = new LinkedHashMap();
	static TreeMap treeMap = new TreeMap();
	static Hashtable hashtable = new Hashtable();
	static Properties properties = new Properties();
	static ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
	public static void main(String[] args) {
		nullKey();
		System.out.println("-------------------------------");
		nullValue();
	}

	private static void nullValue() {
		outValue(hashMap);
		outValue(linkedHashMap);
		outValue(concurrentHashMap);
		outValue(treeMap);
		outValue(hashtable);
		outValue(properties);
	}

	private static void nullKey() {
		outKey(hashMap);
		outKey(linkedHashMap);
		outKey(concurrentHashMap);
		outKey(treeMap);
		outKey(hashtable);
		outKey(properties);
	}

	private static void outKey(Map map) {
		String simpleName = map.getClass().getSimpleName();
		try {
			map.put(null, 1);
			System.out.println(simpleName + "支持key为null");
		} catch (Exception e) {
			System.out.println(simpleName + "《不》支持key为null");
			//e.printStackTrace();
		}

	}

	private static void outValue(Map map) {
		String simpleName = map.getClass().getSimpleName();
		try {
			map.put(1, null);
			System.out.println(simpleName + "支持value为null");
		} catch (Exception e) {
			System.out.println(simpleName + "《不》支持value为null");
			//e.printStackTrace();
		}

	}
}
