package com.smxknife.java2.classloader.class_geli;

/**
 * @author smxknife
 * 2020/12/30
 */
public class TestB {
	public void hello() {
		System.out.println("TestB " + this.getClass().getClassLoader());
	}
}
