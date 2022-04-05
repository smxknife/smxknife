package com.smxknife.java2.collections.map;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2020/7/6
 */
public class EnumMapDemo {
	public static void main(String[] args) {
//		Map<String, String> map = new HashMap();
//		map.put("1", "1");
//		map.put("11", "11");
//		map.put("12", "12");
//		EnumMap enumMap = new EnumMap(map);
//		System.out.println(enumMap);
		System.out.println("上面会报错，在newEnumMap(map)处，原因是EnumMap只能接受key是Enum的map");

		Map<Enum, Object> enumObjectMap = new HashMap<>();
		enumObjectMap.put(E1.E_11, "aaa");
		enumObjectMap.put(E1.E_12, "bbb");
		System.out.println(enumObjectMap);

		EnumMap enumMap = new EnumMap(enumObjectMap);
		System.out.println(enumMap);
		System.out.println(enumMap.get("aaa"));
		System.out.println(enumMap.get("E_11"));
		System.out.println(enumMap.get(E1.E_11));

		enumMap.put(E1.E_13, "ccc");
		System.out.println(enumMap);

		enumMap.put(E2.E_21, "ddd");
		System.out.println(enumMap);

	}

	enum E1 {
		E_11, E_12, E_13;
	}

	enum E2 {
		E_21, E_22, E_23;
	}
}
