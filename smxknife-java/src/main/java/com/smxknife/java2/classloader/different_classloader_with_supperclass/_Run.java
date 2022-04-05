package com.smxknife.java2.classloader.different_classloader_with_supperclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author smxknife
 * 2020/8/21
 */
public class _Run {
	public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		_Run run = new _Run();
		ClassLoader classLoader = run.getClass().getClassLoader();
		Class<?> supperClassLoaderClass = classLoader.loadClass("com.smxknife.java2.classloader.different_classloader_with_supperclass.SupperClassLoader");

		URL baseUrl = supperClassLoaderClass.getResource("");

		Path superPath = Paths.get(baseUrl.getPath(), "super");
		URL superUrl = superPath.toUri().toURL();

		SupperClassLoader supperClassLoader = new SupperClassLoader(new URL[]{superUrl});

		Path subPath = Paths.get(baseUrl.getPath(), "sub");
		URL subUrl = subPath.toUri().toURL();

		ClassLoader subClassLoader = new SubClassLoader(new URL[]{subUrl}, supperClassLoader);


		System.out.println("supperClassLoader | " + supperClassLoader + " | parent = " + supperClassLoader.getParent());
		System.out.println("subClassLoader | " + subClassLoader + " | parent = " + subClassLoader.getParent());

		Class<?> subClass = subClassLoader.loadClass("com.smxknife.java2.classloader.different_classloader_with_supperclass.sub.Sub");
		Constructor<?> subClassDeclaredConstructor = subClass.getDeclaredConstructor();
		Object o = subClassDeclaredConstructor.newInstance();
//		Sub o = (Sub)subClass.newInstance();
		System.out.println(subClass.getClassLoader());
	}
}
