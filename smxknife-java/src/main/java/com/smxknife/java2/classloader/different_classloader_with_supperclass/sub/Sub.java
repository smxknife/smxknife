package com.smxknife.java2.classloader.different_classloader_with_supperclass.sub;

import com.smxknife.java2.classloader.different_classloader_with_supperclass.supper.Supper;

/**
 * @author smxknife
 * 2020/8/21
 */
public class Sub extends Supper {

	static {
		System.out.println("static Sub...");
	}

	public Sub() {
		System.out.println("construct Sub...");
	}
}
