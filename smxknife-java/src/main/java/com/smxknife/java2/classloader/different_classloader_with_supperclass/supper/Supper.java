package com.smxknife.java2.classloader.different_classloader_with_supperclass.supper;

/**
 * @author smxknife
 * 2020/8/21
 */
public class Supper {

	static {
		System.out.println("static Supper...");
	}

	public Supper() {
		System.out.println("construct Supper...");
	}
}
