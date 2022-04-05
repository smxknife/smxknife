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

		// hash(key)是一个关键方法
		/**
		 * static final int hash(Object key) {
		 *         int h;
		 *         return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		 *     }
		 */
		// 关键一点就是 hash ^ (hash >>> 16)， ^(hash >>> 16)可以称为扰动函数，假如在没有扰动函数的情况下
		// hashCode简化为hash = key.hashCode()，在get(key)的时候，采用的是是如下的方式
		/**
		 * tab[(table.length - 1) & hash]
		 */
		// 上面已经说过table.length采用的是2的幂次长度，那么减一之后，
		// 采用二进制表示，就会变成0000...1111，高位全是0，低位全是1的情况
		// hash 与 0000...1111这种形式做与运算，相当于去掉将高位全变为0，这样就很容易出现hash碰撞
		// 对于一些高位不同，低位相同的key，最后的hash却是相同的，这就很不合理，所以，这里采用扰动函数进行干预
		// ^(hash >>> 16) ，hash >>> 16 相当于把int的32位高16位右移到低16位上，这样再原来的低16位可以与原来的高16位
		// 做异或运算，这样相当于高位和低位同时参与低位hash编码，在与table.length做取模运算的时候，减少了hash碰撞的概率

		// 执行过程如下：
		//      1) 判断table是否为null或length=0
		//          1.1) 如果 真，调用resize()方法，对table进行扩容，如果为null进行分配

		String k1 = map.get("k1");
		map.containsKey("k1");
		map.isEmpty();
		map.size();

	}
}
