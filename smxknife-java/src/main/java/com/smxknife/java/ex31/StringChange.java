package com.smxknife.java.ex31;

import com.smxknife.java2.unsafe.UnsafeWrapper;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author smxknife
 * 2020/8/4
 */
public class StringChange {
	public static void main(String[] args) throws NoSuchFieldException {
		String str = "123";
		System.out.println(System.identityHashCode(str));
		Unsafe unsafe = UnsafeWrapper.unsafe();
		Field value = String.class.getDeclaredField("value");
		value.setAccessible(true);
		long valueOffset = unsafe.objectFieldOffset(value);
		Object objectVolatile = unsafe.getObjectVolatile(str, valueOffset);

		int arrayIndexScale = unsafe.arrayIndexScale(char[].class);
		int arrayBaseOffset = unsafe.arrayBaseOffset(char[].class);
		long shift = 31 - Integer.numberOfLeadingZeros(arrayIndexScale);

		unsafe.putCharVolatile(objectVolatile, (long) ((1 << shift) + arrayBaseOffset), '3');
		System.out.println(str);
		System.out.println(System.identityHashCode(str));
	}
}
