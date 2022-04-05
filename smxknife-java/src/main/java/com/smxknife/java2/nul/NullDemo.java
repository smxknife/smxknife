package com.smxknife.java2.nul;

/**
 * @author smxknife
 * 2020/8/6
 */
public class NullDemo {
	public static void main(String[] args) {
		NullDemo nullDemo = null;
		nullDemo.print("hello");

		Integer integer = null;
		// int i = integer; // 拆箱空指针异常
		// System.out.println(i);

		System.out.println(null == null);
		System.out.println(null != null);

		System.out.println(null instanceof Number);

	}

	private static void print(String obj) {
		System.out.println(obj);
	}
}
