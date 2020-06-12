package com.smxknife.java2.classloader.serviceloader;

/**
 * @author smxknife
 * 2019/11/6
 */
public class MyDriver implements Driver {

	public MyDriver(String name) {
		System.out.println("hhh construct");
	}

	@Override
	public String name() {
		return this.getClass().getCanonicalName();
	}
}
