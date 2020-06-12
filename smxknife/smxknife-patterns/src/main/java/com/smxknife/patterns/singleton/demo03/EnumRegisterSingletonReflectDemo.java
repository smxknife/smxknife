package com.smxknife.patterns.singleton.demo03;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author smxknife
 * 2019/12/26
 */
public class EnumRegisterSingletonReflectDemo {
	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//		Constructor<EnumRegisterSingleton> constructor = EnumRegisterSingleton.class.getDeclaredConstructor();
//		constructor.setAccessible(true);
//
//		EnumRegisterSingleton newInstance = constructor.newInstance();
//		System.out.println(newInstance);
//		System.out.println("根本不会输出到这里，可以看出枚举可以很好的防止反射获取多个对象");

		System.out.println("上面找不到构造函数，是因为没有无参构造函数，Enum是有带参的构造函数");
		Constructor<EnumRegisterSingleton> constructor = EnumRegisterSingleton.class.getDeclaredConstructor(String.class, int.class);
		constructor.setAccessible(true);
		EnumRegisterSingleton hello = constructor.newInstance("Hello", 0);
		System.out.println("这里依然不会被运行到，会抛出Cannot reflectively create enum objects的异常消息" +
				"所以，枚举还是很安全的");

		EnumRegisterSingleton[] enumConstants = EnumRegisterSingleton.class.getEnumConstants();
		EnumRegisterSingleton enumConstant = enumConstants[0];
		System.out.println(enumConstant == EnumRegisterSingleton.INSTANCE);
		System.out.println("通过反射获取不到多个EnumRegisterSingleton，那就反射获取INSTANCE试试，" +
				"其实不用试也知道，肯定会相等，因为该属性类相关，不管怎么用都是一个，除非classLoader不是同一个");
	}
}
