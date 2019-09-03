package com.smxknife.java2.collections.map;

import java.util.HashMap;

/**
 * @author smxknife
 * 2019/9/2
 */
public class HashMapDemo {
	public static void main(String[] args) {

		/**
		 * static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; 默认容量 16
		 *  static final int MAXIMUM_CAPACITY = 1 << 30; 最大容量
		 *  static final float DEFAULT_LOAD_FACTOR = 0.75f; 加载因子
		 *  static final int TREEIFY_THRESHOLD = 8; 升级为树结构的阈值
		 *  static final int UNTREEIFY_THRESHOLD = 6; 从树结构降级阈值
		 *  static final int MIN_TREEIFY_CAPACITY = 64; 最小容量
		 *
		 *  transient Node<K,V>[] table; 初始化分配大小，动态扩展，大小始终保持2的幂次
		 *  transient Set<Map.Entry<K,V>> entrySet; 缓存entrySet
		 *  transient int size; map的大小
		 *  int threshold; 负载阈值(capacity * load factor).
		 *  final float loadFactor; hash table的加载因子
		 */
		HashMap<String, String> map = new HashMap<>();

		// 初始化会进行上面参数的初始化
		// this.threshold = tableSizeFor(initialCapacity);
		/**
		 * static final int tableSizeFor(int cap) {
		 *         int n = cap - 1;
		 *         n |= n >>> 1;
		 *         n |= n >>> 2;
		 *         n |= n >>> 4;
		 *         n |= n >>> 8;
		 *         n |= n >>> 16;
		 *         return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
		 *     }
		 */
		// 通过上面的处理，将指定的容量处理成2的幂次

		System.out.println("put -------");
		map.put("k1", "v1");
		// put是一个复杂的操作，调用了一个put(int hash, K key, V value, boolean onlyIfAbsent, boolean evict)
		//      onlyIfAbsent: 如果为true，表示不改变已经存在的值
		//      evict: 如果为false表示table处于创建模式
		// 所以在默认调用的时候，put(hash("k1"), "k1", "v1", false, true)
		// 执行过程如下：
		//      1) 判断table是否为null或length=0
		//          1.1) 如果 真，调用resize()方法，对table进行扩容，如果为null进行分配
	}
}
