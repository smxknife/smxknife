package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019/10/14
 */
public class Demo_loadclass_classforname {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("=== Class.forName(name) ==============");
		Class.forName("com.smxknife.java2.classloader.demo01.LoadObject");
	}
}
