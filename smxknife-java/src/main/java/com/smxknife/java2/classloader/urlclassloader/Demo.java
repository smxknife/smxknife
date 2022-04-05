package com.smxknife.java2.classloader.urlclassloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author smxknife
 * 2020/8/20
 */
public class Demo {
	public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		String userDir = System.getProperty("user.dir");
		File file = new File(userDir);
		URI uri = file.toURI();
		System.out.println(uri);
		//
		String fileUrlString = file.toURI().toString();
		// 因为这里classloader里面不允许有!/
		fileUrlString = fileUrlString.replaceAll("!/", "%21/");

		URL[] urls = new URL[] {new URL(fileUrlString)};
		URLClassLoader classLoader = new URLClassLoader(urls);
		Class<?> helloClass = classLoader.loadClass("com.smxknife.java2.classloader.urlclassloader.Hello");
		Method sayHello = helloClass.getDeclaredMethod("sayHello", null);
		sayHello.invoke(helloClass);
	}
}
