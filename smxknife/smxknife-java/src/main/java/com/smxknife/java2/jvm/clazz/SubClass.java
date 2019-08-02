package com.smxknife.java2.jvm.clazz;

/**
 * @author smxknife
 * 2019-07-01
 */
public class SubClass extends SuperClass {

	public static int k = 1;

	static {
		System.out.println("SubClass init");
	}

}
