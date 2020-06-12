package com.smxknife.java2.classloader.interfaceInDifferentClassloader;

/**
 * @author smxknife
 * 2019/12/29
 */
public class Main {
	public static void main(String[] args) throws ClassNotFoundException {
		ClassLoaderA loaderA = new ClassLoaderA();
		Class<?> iAClass = loaderA.loadClass(InterfaceDemo.class.getSimpleName());
		Class<?> cAClass = loaderA.loadClass(InterfaceDemoImpl.class.getSimpleName());

		ClassLoaderB loaderB = new ClassLoaderB();
		Class<?> iBClass = loaderB.loadClass(InterfaceDemo.class.getSimpleName());
		Class<?> cBClass = loaderB.loadClass(InterfaceDemoImpl.class.getSimpleName());

		System.out.println(iAClass);
		System.out.println(iAClass.getClassLoader());
		System.out.println("以上 -- A classloader load interface");
		System.out.println(iBClass);
		System.out.println(iBClass.getClassLoader());
		System.out.println("以上 -- B classloader load interface");
		System.out.println(iAClass.equals(iBClass));

		System.out.println(cAClass);
		System.out.println(cAClass.getClassLoader());
		System.out.println("以上 -- A classloader load class");
		System.out.println(cBClass);
		System.out.println(cBClass.getClassLoader());
		System.out.println("以上 -- B classloader load class");
		System.out.println(cAClass.equals(cBClass));
	}
}
