package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019-01-29
 */
public class Demo_out_all_classloader {
	public static void main(String[] args) {
		ClassLoader classLoader = Demo_out_all_classloader.class.getClassLoader();
		System.out.println(classLoader);
		System.out.println(classLoader.getParent());
		System.out.println(classLoader.getParent().getParent());
	}
}
