package com.smxknife.java.ex31;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/7/27
 */
public class ConcurrentHashMapLargePut {
	public static void main(String[] args) {
		int cap = 1_000_000;
		HashMap<Integer, Integer> hashMap = new HashMap<>(cap);
		Stream.iterate(0, idx -> idx + 1).limit(cap).forEach(idx -> {
			hashMap.put(idx, idx * 10);
		});

//		test1(hashMap);
		test2(hashMap);
		test3(hashMap);
//		test4(hashMap);
//		test5(hashMap);
		hashMap.clear();

	}

	private static void test1(HashMap<Integer, Integer> hashMap) {
		int size = hashMap.size();
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
		long start = System.currentTimeMillis();
		hashMap.entrySet().forEach(entry -> {
			map.put(entry.getKey(), entry.getValue());
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		map.clear();
	}

	private static void test2(HashMap<Integer, Integer> hashMap) {
		int size = hashMap.size();
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(size);
		long start = System.currentTimeMillis();
		hashMap.entrySet().forEach(entry -> {
			map.put(entry.getKey(), entry.getValue());
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		map.clear();
	}

	private static void test3(HashMap<Integer, Integer> hashMap) {
		int size = hashMap.size();
		int sizeFor = tableSizeFor(size);
		System.out.println(sizeFor);
		System.out.println(sizeFor >>> 2);
		System.out.println(sizeFor * 0.75);
		sizeFor += (sizeFor >>> 2);
		System.out.println(sizeFor);
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(sizeFor );
		long start = System.currentTimeMillis();
		hashMap.entrySet().forEach(entry -> {
			map.put(entry.getKey(), entry.getValue());
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		map.clear();
	}

	private static void test4(HashMap<Integer, Integer> hashMap) {
		int size = hashMap.size();
		Set<Integer> keySet = hashMap.keySet();
		int sizeFor = tableSizeFor(size);
		sizeFor += sizeFor >>> 2;
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(size);
//		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(sizeFor);
		ConcurrentHashMap.KeySetView<Integer, Integer> keySetView = map.keySet(0);
		long start = System.currentTimeMillis();
		keySetView.addAll(keySet);
		hashMap.entrySet().forEach(entry -> {
			map.put(entry.getKey(), entry.getValue());
		});
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		map.clear();
	}

	private static void test5(HashMap<Integer, Integer> hashMap) {
		int size = hashMap.size();
		int sizeFor = tableSizeFor(size);
		sizeFor += sizeFor >>> 2;
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(size );
//		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>(sizeFor );
		long start = System.currentTimeMillis();
		map.putAll(hashMap);
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		map.clear();
	}

	private static final int tableSizeFor(int c) {
		int n = c - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		return (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
	}
}
