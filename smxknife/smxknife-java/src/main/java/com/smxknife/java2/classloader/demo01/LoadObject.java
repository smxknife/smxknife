package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019/10/14
 */
public class LoadObject {
	static {
		System.out.println("LoadObject static{}");
	}

	public LoadObject() {
		System.out.println("LoadObject constructor");
	}
}
