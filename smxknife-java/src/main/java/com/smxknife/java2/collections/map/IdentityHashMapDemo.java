package com.smxknife.java2.collections.map;

import java.util.IdentityHashMap;

/**
 * @author smxknife
 * 2020/7/6
 */
public class IdentityHashMapDemo {
	public static void main(String[] args) {
		IdentityHashMap<String, String> map = new IdentityHashMap();
		map.put("a", "1");
		map.put("a", "2");
		map.put("a", "3");
		System.out.println(map);
		System.out.println("从输出结果可以看出，如果完全相同的key，map会更新值");

		map.put(new String("a"), "11");
		map.put(new String("a"), "12");
		map.put(new String("a"), "13");
		System.out.println(map);

		System.out.println("从上面看，虽然重写了hashCode方法，但是还是被当做三个不同的key来处理，其实看名字有可能会联想到System.identityHashCode(obj)，根据对象的内存地址来计算hash");
		System.out.println("从源码中发现，确实是上面的联想，采用的System.identityHashCode来计算hash");

		System.out.println("下面试一下自动封箱类型的key");
		IdentityHashMap<Integer, Integer> integerMap = new IdentityHashMap<>();
		integerMap.put(1, 1);
		integerMap.put(1, 2);
		integerMap.put(1, 3);
		System.out.println(integerMap);
		System.out.println("之前在WeakHashMap中已经看过，[-128, 127]这个范围的数字是比较特殊的，下面试一下这个范围外的");
		integerMap.put(128, 128);
		integerMap.put(128, 1281);
		integerMap.put(128, 1282);
		System.out.println(integerMap);
		System.out.println("从结果来看，范围外的通过自动封箱后，被认为是不同的对象，所以内存地址不相同，结果就保存了三份");
		System.out.println("下面再试试范围内，不自动封箱，而是人工创建Integer对象，可以想象应该是保存多个值");
		integerMap.put(new Integer(127), 127);
		integerMap.put(new Integer(127), 1271);
		integerMap.put(new Integer(127), 1272);
		System.out.println(integerMap);
		System.out.println("验证正确");

	}
}
