package com.smxknife.java2.classloader.class_geli;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2020/12/30
 */
public class GeliTest_loadClass {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		GeliClassLoader_loadClass classLoader = new GeliClassLoader_loadClass(Thread.currentThread().getContextClassLoader().getParent());
		Class<?> testAClass = classLoader.loadClass("com.smxknife.java2.classloader.class_geli.TestA");
		Method main = testAClass.getDeclaredMethod("main", String[].class);
		main.invoke(null, new Object[]{args});
	}
}
