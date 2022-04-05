package com.smxknife.java.ex24;

/**
 * @author smxknife
 * 2019-02-01
 */
public class Demo2 {
	public static void main(String[] args) {
		System.out.println(1 & 0xff);
		System.out.println(12 & 0xff);
		System.out.println(123 & 0xff);
		System.out.println(1234 & 0xff);
		System.out.println(Integer.toBinaryString(1234));
		System.out.println(Integer.toBinaryString(0xff));
		System.out.println(255 & 0xff);
		System.out.println(256 & 0xff);
		System.out.println(257 & 0xff);
		System.out.println(258 & 0xff);
		System.out.println(259 & 0xff);
		System.out.println(260 & 0xff);
		System.out.println(261 & 0xff);
		System.out.println(262 & 0xff);

		int num = 255;
		System.out.println(num * 2 & 0xff);
		System.out.println((num * 2) & 0xff);
		System.out.println((num * 2 + 1) & 0xff);
		System.out.println((num * 2 + 2) & 0xff);
		System.out.println((num * 2 + 3) & 0xff);
		System.out.println((num * 2 + 4) & 0xff);

		System.out.println(0xff);
	}
}
