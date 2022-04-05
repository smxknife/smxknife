package com.smxknife.java2.classloader.different_classloader_with_supperclass;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author smxknife
 * 2020/8/21
 */
public class SubClassLoader extends URLClassLoader {

	public SubClassLoader(URL[] urls, ClassLoader classLoader) {
		super(urls, classLoader);
	}
}
