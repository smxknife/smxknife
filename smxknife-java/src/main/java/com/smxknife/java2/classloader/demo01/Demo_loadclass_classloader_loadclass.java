package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019/10/14
 */
public class Demo_loadclass_classloader_loadclass {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("=== ClassLoader.loadClass ==============");
		Demo_loadclass_classloader_loadclass.class.getClassLoader().loadClass("com.smxknife.java2.classloader.demo01.LoadObject");
	}
}
