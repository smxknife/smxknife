package com.smxknife.java2.collections.map;

import java.util.TreeMap;

/**
 * @author smxknife
 * 2020/7/6
 */
public class TreeMapDemo {
	public static void main(String[] args) {
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(1, 1);
		treeMap.put(3, 3);
		treeMap.put(2, 2);
		treeMap.put(10, 10);
		treeMap.put(8, 8);
		treeMap.put(13, 13);
		treeMap.get(1);
		System.out.println(treeMap);

		System.out.println("sortedMap 相关方法 ===========");
		System.out.println("subMap(2, 8): " + treeMap.subMap(2, 8));
		System.out.println("headMap(3): " + treeMap.headMap(3));
		System.out.println("tailMap(3): " + treeMap.tailMap(3));
		System.out.println("firstKey(): " + treeMap.firstKey());
		System.out.println("lastKey(): " + treeMap.lastKey());

		System.out.println("navigableMap 相关方法 ========");
		System.out.println("lowerEntry(3): " + treeMap.lowerEntry(3));
		System.out.println("lowerEntry(4): " + treeMap.lowerEntry(4));
		System.out.println("higherEntry(3): " + treeMap.higherEntry(3));
		System.out.println("higherEntry(4): " + treeMap.higherEntry(4));
		System.out.println("floorEntry(3): " + treeMap.floorEntry(3));
		System.out.println("floorEntry(4): " + treeMap.floorEntry(4));
		System.out.println("ceilingEntry(3): " + treeMap.ceilingEntry(3));
		System.out.println("ceilingEntry(4): " + treeMap.ceilingEntry(4));
		System.out.println("firstEntry(): " + treeMap.firstEntry());
		System.out.println("lastEntry(): " + treeMap.lastEntry());
		System.out.println("descendingMap(): " + treeMap.descendingMap());
		System.out.println("navigableKeySet(): " + treeMap.navigableKeySet());
		System.out.println("pollFirstEntry(): " + treeMap.pollFirstEntry() + " - " + treeMap);
		System.out.println("pollLastEntry(): " + treeMap.pollLastEntry() + " - " + treeMap);
	}
}
