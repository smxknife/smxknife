package com.smxknife.java.ex30;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019/12/30
 */
public class MapConcurrentModificationException {
	public static void main(String[] args) {
		Map<Integer, Object> map = new HashMap<>();
		map.put(-1, -1);
		for (Integer integer : map.keySet()) {
			System.out.println(map);
			map.put(integer + 1, integer + 1);
			System.out.println(map);
		}
	}
}
