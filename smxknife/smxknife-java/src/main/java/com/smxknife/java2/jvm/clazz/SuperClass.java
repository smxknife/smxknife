package com.smxknife.java2.jvm.clazz;

/**
 * @author smxknife
 * 2019-07-01
 */
public class SuperClass {

	static {
		System.out.println("SuperClass init");
		value = 1;
//		System.out.println(value);
	}

	public static int value = 123;

//	public static void test() {
//		System.out.println("SuperClass test");
//	}
}
