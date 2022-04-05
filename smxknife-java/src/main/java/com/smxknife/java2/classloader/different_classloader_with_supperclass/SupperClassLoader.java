package com.smxknife.java2.classloader.different_classloader_with_supperclass;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author smxknife
 * 2020/8/21
 */
public class SupperClassLoader extends URLClassLoader {

	public SupperClassLoader(URL[] urls) {
		super(urls);
	}
}
