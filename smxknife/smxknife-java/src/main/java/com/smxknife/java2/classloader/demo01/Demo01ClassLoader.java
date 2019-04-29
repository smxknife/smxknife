package com.smxknife.java2.classloader.demo01;

/**
 * @author smxknife
 * 2019-01-29
 */
public class Demo01ClassLoader extends ClassLoader {

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		return super.findClass(name);
	}
}
