package com.smxknife.java.ex31;

import java.util.HashMap;

/**
 * @author smxknife
 * 2020/7/8
 */
public class HashMapPutInAbsentDemo {
	public static void main(String[] args) {
		HashMap<Integer, Obj> map = new HashMap<>();
		Obj paramObj1 = new Obj();
		Obj obj1 = map.putIfAbsent(1, paramObj1);
		System.out.println("paramObj1 = " + paramObj1);
		System.out.println("obj1 = " + obj1);
		System.out.println("obj1 == paramObj1 " + (obj1 == paramObj1));

		Obj paramObj2 = new Obj();
		Obj obj2 = map.putIfAbsent(1, paramObj2);
		System.out.println("paramObj2 = " + paramObj2);
		System.out.println("obj2 = " + obj2);
		System.out.println("obj2 = paramObj2 " + (obj2 == paramObj2));
		System.out.println("obj2 = paramObj1 " + (obj2 == paramObj1));
		System.out.println("obj2 = obj1" + (obj2 == obj1));

		Obj obj3 = map.putIfAbsent(1, newObj());
	}

	private static Obj newObj() {
		return new Obj();
	}

	static class Obj {
		public Obj() {
			//System.out.println("...obj ");
		}
	}
}
