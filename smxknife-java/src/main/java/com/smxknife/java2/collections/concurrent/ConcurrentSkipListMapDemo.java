package com.smxknife.java2.collections.concurrent;

import java.util.Map;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author smxknife
 * 2019/9/9
 */
public class ConcurrentSkipListMapDemo {
	public static void main(String[] args) {
		ConcurrentSkipListMap<Integer, Integer> map = new ConcurrentSkipListMap();
		map.put(1, 10);
		map.put(3, 30);
		map.put(5, 50);
		map.put(7, 70);
		System.out.println(map);

		ConcurrentNavigableMap<Integer, Integer> navigableMap = map.descendingMap();
		System.out.println("navigableMap = " + navigableMap);
		System.out.println("map = " + map);
		NavigableSet<Integer> navigableKeySet = map.navigableKeySet();
		System.out.println("navigableKeySet = " + navigableKeySet);

		Map.Entry<Integer, Integer> firstEntry = map.firstEntry();
		System.out.println("firstEntry: " + firstEntry);
		System.out.println("firstEntry(null): " + new ConcurrentSkipListMap<>().firstEntry());
		Map.Entry<Integer, Integer> lastEntry = map.lastEntry();
		System.out.println("lastEntry: " + lastEntry);

		System.out.println("map = " + map);

		Map.Entry<Integer, Integer> pollFirstEntry = map.pollFirstEntry();
		System.out.println("pollFirstEntry = " + pollFirstEntry + " | map = " + map);
		System.out.println("pollFirstEntry(null) = " + new ConcurrentSkipListMap<>().pollFirstEntry());

		Map.Entry<Integer, Integer> pollLastEntry = map.pollLastEntry();
		System.out.println("pollLastEntry = " + pollLastEntry + " | map = " + map) ;

		Integer ceilingKey = map.ceilingKey(4); // 大于等于该值
		System.out.println("ceilingKey(4) = " + ceilingKey);
		System.out.println("ceilingKey(5) = " + map.ceilingKey(5));

		System.out.println("higherKey(4) = " + map.higherKey(4));
		System.out.println("higherKey(3) = " + map.higherKey(3));
		System.out.println("higherKey(5) = " + map.higherKey(5));

		Map.Entry<Integer, Integer> ceilingEntry = map.ceilingEntry(4);
		System.out.println("ceilingEntry = " + ceilingEntry);

		Integer floorKey = map.floorKey(4); // 小于等于该值
		System.out.println("floorKey(4) = " + floorKey);
		System.out.println("floorKey(5) = " + map.floorKey(5));

		System.out.println("lowerKey(4) = " + map.lowerKey(4));
		System.out.println("lowerKey(3) = " + map.lowerKey(3));
		System.out.println("lowerKey(5) = " + map.lowerKey(5));

		ConcurrentNavigableMap<Integer, Integer> headMap = map.headMap(4);
		System.out.println("headMap(4) = " + headMap);
		System.out.println("headMap(5) = " + map.headMap(5));
		System.out.println("headMap(5, include) = " + map.headMap(5, true));
	}
}
