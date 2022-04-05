package com.smxknife.java2.collections.concurrent;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2019/9/2
 */
public class ConcurrentHashMapDemo {
	public static void main(String[] args) {
		ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();

		System.out.println("==== putIfAbsent ====");
		Integer integer = map.putIfAbsent(1, 10);
		System.out.println(map + " | " + integer);
		integer = map.putIfAbsent(1, 11);
		System.out.println(map + " | " + integer);
		System.out.println("==== putIfAbsent只有在key不存在时，才能添加成功，成功是返回null，不成功时返回已经存在的值");

		System.out.println("==== compute ====");
		integer = map.compute(1, (v1, v2) -> {
			System.out.println("v1 = " + v1);
			System.out.println("v2 = " + v2);
			return v2;
		});
		System.out.println(map + " | " + integer);
		integer = map.compute(1, (key, val) -> {
			System.out.println("key = " + key);
			System.out.println("val = " + val);
			return val + key;
		});
		System.out.println(map + " | " + integer);
		integer = map.compute(2, (key, val) -> {
			System.out.println("key = " + key);
			System.out.println("val = " + val);
			return key;
		});
		System.out.println(map + " | " + integer);
		System.out.print("==== compute 用于对key的值进行重计算，key可以存在，也可以不存在，但是如果不存在时，key对应的value为null;\r\n");
		System.out.println("     compute总共有两个参数，第一个为key，第二个为一个lambda表达式BiFunction，两个参数一个返回值，两个参数分别表示key和value");

		System.out.println("==== computeIfAbsent ====");
		integer = map.computeIfAbsent(1, (key) -> {
			System.out.println("key = " + key); // 因为key已经存在，所以lambda都不会执行
			return null;
		});
		System.out.println(map + " | " + integer);

		integer = map.computeIfAbsent(3, (key) -> {
			System.out.println("key = " + key); // 还有一点要注意，这里参数是key，而不是value，之前就已经误以为错误value
			return null;
		});
		System.out.println(map + " | " + integer);
		integer = map.computeIfAbsent(3, (key) -> {
			System.out.println("key = " + key); // 还有一点要注意，这里参数是key，而不是value，之前就已经误以为错误value
			return 3;
		});
		System.out.println(map + " | " + integer);
		System.out.println("==== computeIfAbsent 只有在key不存在的时候才会执行lambda，lambda可以返回null，返回null时没有任何副作用");

		System.out.println("==== computeIfPresent ====");
		integer = map.computeIfPresent(4, (key, val) -> {
			System.out.println("key = " + key);
			System.out.println("val = " + val);
			return null;
		});
		System.out.println(map + " | " + integer);

		integer = map.computeIfPresent(1, (key, val) -> {
			System.out.println("key = " + key);
			System.out.println("val = " + val);
			return key + val;
		});
		System.out.println(map + " | " + integer);

		integer = map.computeIfPresent(3, (key, val) -> {
			System.out.println("key = " + key);
			System.out.println("val = " + val);
			return null;
		});
		System.out.println(map + " | " + integer);
		System.out.println("==== computeIfPresent 只有key存在才会进行处理，如何处理取决于lambda返回的结果，如果lambda返回为null，相当于删除；如果非null进行数据替换");

		System.out.println("==== mappingCount ====");
		long l = map.mappingCount();
		System.out.println(l);
		System.out.println("==== mappingCount size的替代方法，防止int类型溢出");

		System.out.println("==== keySet ====");
		ConcurrentHashMap.KeySetView<Integer, Integer> integers = map.keySet(20);
		System.out.println(integers);
		System.out.println(map);
		boolean add = integers.add(10);
		System.out.println(integers);
		System.out.println(map);
		integers.addAll(Arrays.asList(2, 3, 4));
		System.out.println(integers);
		System.out.println(map);
		integers.remove(4);
		System.out.println(integers);
		System.out.println(map);
		System.out.println("==== keySet 这是一个key的视图操作器，mappedValue对应的是通过该视图添加key的默认值。这个在某些场景下相当便利");

		ConcurrentHashMap.KeySetView<Integer, Integer> integers1 = map.keySet();
		// integers1.add(4); // 报错了
		integers1.remove(10);
		System.out.println(integers1);
		System.out.println(map);

		System.out.println("==== merge ====");
		integer = map.merge(4, 41, (oriVal, newVal) -> {
			System.out.println("oriVal = " + oriVal);
			System.out.println("newVal = " + newVal);
			return null;
		});
		System.out.println(map + " | " + integer);

		integer = map.merge(4, 40, (oriVal, newVal) -> {
			System.out.println("oriVal = " + oriVal);
			System.out.println("newVal = " + newVal);
			return oriVal + newVal;
		});
		System.out.println(map + " | " + integer);

		integer = map.merge(4, 40, (oriVal, newVal) -> {
			System.out.println("oriVal = " + oriVal);
			System.out.println("newVal = " + newVal);
			return null;
		});
		System.out.println(map + " | " + integer);
		System.out.println("==== merge 很类似computeIfPresent，很大的不同点是lambda的表达式参数，两个都为value值，不是key");

		System.out.println("==== reduce ====");
		map.put(4, 40);
		map.put(5, 50);
		integer = map.reduce(4, (key, val) -> {
			// System.out.println(Thread.currentThread().getName() + " | key = " + key + ", val = " + val);
			return val;
		}, (pre, next) -> {
			System.out.println(Thread.currentThread().getName() + " | pre = " + pre + ", next = " + next);
			return next;
		});
		System.out.println(map + " | " + integer);

		String res = map.reduce(4, (key, val) -> {
			// System.out.println(Thread.currentThread().getName() + " | key = " + key + ", val = " + val);
			return key + "_" + val;
		}, (pre, next) -> {
			System.out.println(Thread.currentThread().getName() + " | pre = " + pre + ", next = " + next);
			return pre + "|" + next;
		});
		System.out.println(map + " | " + res);
		System.out.println("==== reduce 操作还是有点小复杂的，看技能树文档吧，里面有清晰的描述");

		Map.Entry<Integer, Integer> integerIntegerEntry = map.reduceEntries(4, (e1, e2) -> {
			System.out.println("e1 = " + e1);
			System.out.println("e2 = " + e2);
			return e1;
		});
		System.out.println(integerIntegerEntry);
		System.out.println("-----");
		Map.Entry<Integer, Integer> integerIntegerEntry1 = map.reduceEntries(1, entry -> {
			System.out.println("entry = " + entry);
			return entry;
		}, (e1, e2) -> {
			System.out.println("e1 = " + e1);
			System.out.println("e2 = " + e2);
			return e1;
		});
		System.out.println(integerIntegerEntry1);

		System.out.println("==== remove(key, val) ====");
		System.out.println(map);
		boolean remove = map.remove(6, 60);
		System.out.println(map + " | " + remove);
		remove = map.remove(5, 60);
		System.out.println(map + " | " + remove);
		remove = map.remove(5, 50);
		System.out.println(map + " | " + remove);
		System.out.println("==== remove 该remove方法必须同时保证key和value都等于map里面key和value，这样才可以删除");

		System.out.println("==== search ====");
		res = map.search(4, (key, val) -> {
			System.out.println(Thread.currentThread().getName() + "key = " + key + ", val = " + val);
			return key + "-" + val;
		});
		System.out.println(map + " | " + res);

		res = map.search(4, (key, val) -> {
			System.out.println(Thread.currentThread().getName() + "key = " + key + ", val = " + val);
			return null;
		});
		System.out.println(map + " | " + res);


	}
}
