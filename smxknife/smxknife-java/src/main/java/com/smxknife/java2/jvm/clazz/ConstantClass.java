package com.smxknife.java2.jvm.clazz;

/**
 * @author smxknife
 * 2019-07-01
 */
public class ConstantClass {

	static {
		System.out.println("ConstantClass init");
	}

	public static final int VALUE = 123;
}
