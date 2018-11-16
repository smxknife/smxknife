package com.smxknife.java.ex11;

import java.lang.reflect.Field;

public class IntegerSwap {

	public static void main(String[] args) {
		System.out.println();
		Integer a = 1;
		Integer b = 2;
		System.out.printf("a = %s, b = %s\n", a, b);
		swap(a, b);
		System.out.printf("a = %s, b = %s\n", a, b);
	}

	public static void swap(Integer a, Integer b) {
		int temp = a.intValue();
		try {
			Field value = Integer.class.getDeclaredField("value");
			value.setAccessible(true);
			value.set(a, new Integer(b.intValue()));
			value.set(b, temp);

		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
