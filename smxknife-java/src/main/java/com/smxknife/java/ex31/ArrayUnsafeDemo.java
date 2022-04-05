package com.smxknife.java.ex31;

import com.smxknife.java2.unsafe.UnsafeWrapper;
import sun.misc.Unsafe;

/**
 * @author smxknife
 * 2020/7/22
 */
public class ArrayUnsafeDemo {
	static Unsafe unsafe = UnsafeWrapper.unsafe();
	static int indexScale = unsafe.arrayIndexScale(Object[].class);
	static long baseOffset = unsafe.arrayBaseOffset(Object[].class);
	static int shift = 31 - Integer.numberOfLeadingZeros(indexScale);
	public static void main(String[] args) {
		Object[] ints = new Object[5];
		ints[0] = 10;
		ints[1] = 11;
		ints[2] = 12;
		ints[3] = 13;

		Object object = unsafe.getObjectVolatile(ints, (0 << shift) + baseOffset);
		System.out.println(object);
	}
}
