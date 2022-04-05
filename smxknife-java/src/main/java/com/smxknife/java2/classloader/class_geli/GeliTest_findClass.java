package com.smxknife.java2.classloader.class_geli;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2020/12/30
 */
public class GeliTest_findClass {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		GeliClassLoader_findClass classLoader = new GeliClassLoader_findClass();
		Class<?> testAClass = classLoader.findClass("com.smxknife.java2.classloader.class_geli.TestA");
		Method main = testAClass.getDeclaredMethod("main", String[].class);
		main.invoke(null, new Object[]{args});
	}
}
