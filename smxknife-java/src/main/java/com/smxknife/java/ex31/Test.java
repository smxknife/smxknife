package com.smxknife.java.ex31;

import com.smxknife.java2.unsafe.UnsafeWrapper;
import sun.misc.Unsafe;

/**
 * @author smxknife
 * 2020/7/22
 */
public class Test {
	static int a = 3, b;

	static int e, d = 5;
	public static void main(String[] args) {
		String binaryString = Integer.toBinaryString(0x7fffffff);
		System.out.println(binaryString);
		System.out.println(binaryString.length());

		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));

		System.out.println(0x7fffffff & 0x80000000);

		Class<int[]> aClass = int[].class;
		Unsafe unsafe = UnsafeWrapper.unsafe();
		int i = unsafe.arrayBaseOffset(aClass);
		System.out.println("arrayBaseOffset = " + i);
		int scale = unsafe.arrayIndexScale(aClass);
		System.out.println("arrayIndexScale = " + scale);

		Class<long[]> longClass = long[].class;
		unsafe = UnsafeWrapper.unsafe();
		int baseOffset = unsafe.arrayBaseOffset(longClass);
		System.out.println("arrayBaseOffset = " + baseOffset);
		int longScale = unsafe.arrayIndexScale(longClass);
		System.out.println("arrayIndexScale = " + longScale);

		int i1 = Integer.numberOfLeadingZeros(scale);
		int i2 = Integer.numberOfTrailingZeros(scale);
		System.out.println(i1);
		System.out.println(i2);

		System.out.println(1 << 15);
		System.out.println(Integer.toBinaryString(1 << 15));

		System.out.println("----------------");
		int c = 8;
		int numZeros = Integer.numberOfLeadingZeros(c);
		System.out.println(numZeros);
		int newC = numZeros | (1 << 15);
		System.out.println(newC);
		System.out.println(Integer.toBinaryString(c));
		System.out.println(Integer.toBinaryString(newC));

		System.out.println(a);
		System.out.println(b);
		System.out.println(e);
		System.out.println(d);

		System.out.println(8 >>> 3);

		int qwe = 1;
		System.out.println(qwe <<=1 );

		int sshift = 0;
		int ssize = 1;
		while (ssize < 16) {
			++sshift;
			ssize <<= 1;
		}
		System.out.println("sshift = " + sshift);
		System.out.println("ssize = " + ssize);

	}
}
