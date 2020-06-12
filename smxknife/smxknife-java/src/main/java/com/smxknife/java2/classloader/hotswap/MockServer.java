package com.smxknife.java2.classloader.hotswap;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/10/31
 */
public class MockServer {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {

		int i = 0;
		while (true) {
			DynamicClassLoader dynamicClassLoader = new DynamicClassLoader();
			Class<?> aClass = dynamicClassLoader.loadClass("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/src/main/resources/cls", "com.smxknife.java2.classloader.hotswap.Target");
			Method out = aClass.getMethod("out", String.class);
			System.out.println(out.invoke(aClass.newInstance(), "no." + (i++)));
			TimeUnit.SECONDS.sleep(3);
		}
	}
}
