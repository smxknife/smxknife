package com.smxknife.java.ex30;

import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019/10/6
 */
public class MapObjectKey {
	public static void main(String[] args) {
		Map<TT, Integer> map = new HashMap<>();
		TT tt = new TT();
		tt.age = 1;
		map.put(tt, tt.age);

		System.out.println(map);
		System.out.println(map.get(tt));

		tt.age = 2;
		System.out.println(map);
		System.out.println(map.get(tt));
	}

	static class TT {
		int age;

		@Override
		public int hashCode() {
			return age;
		}
	}
}
