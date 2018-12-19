package com.smxknife.asm.demo02;

/**
 * @author smxknife
 * 2018-12-18
 */
public class MyClassLoader extends ClassLoader {

	public Class defineClass(String name, byte[] bytes) {
		return defineClass(name, bytes, 0, bytes.length);
	}
}
