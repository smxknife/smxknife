package com.smxknife.java2.string;

import java.lang.reflect.Field;

/**
 * @author smxknife
 * 2021/5/6
 */
public class StringChange2 {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
		String str = "123";
		System.out.println(System.identityHashCode(str));

		// 1。 string没有提供直接修改值的方法，所有的修改，都会new一个新的对象
		//str = directChange(str, "333".toCharArray());

		// 2。 反射修改
		changeStringValue(str, "333".toCharArray());

		System.out.println(str);
		System.out.println(System.identityHashCode(str));

	}

	public static String directChange(String str, char[] newValue) {
		System.out.println("param str: " + System.identityHashCode(str));
		str = str.replace(str, new String(newValue));
		return str;
	}

	public static String changeStringValue(String str, char[] newValue) throws NoSuchFieldException, IllegalAccessException {
		System.out.println("param str: " + System.identityHashCode(str));
		final Field valueField = str.getClass().getDeclaredField("value");
		valueField.setAccessible(true);
		valueField.set(str, newValue);
		return str;
	}
}
