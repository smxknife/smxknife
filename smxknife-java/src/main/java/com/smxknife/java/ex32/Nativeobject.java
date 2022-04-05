package com.smxknife.java.ex32;

/**
 * @author smxknife
 * 2020/10/27
 */
public class Nativeobject {
	public static void main(String[] args) {
		long var4 = 140375923487232L;
		int var3 = 4096;

		System.out.println("var4 十进制：" + var4);
		System.out.println("var4 二进制：" + Long.toBinaryString(var4));

		System.out.println("var3 十进制：" + var3);
		System.out.println("var3 二进制：" + Integer.toBinaryString(var3));

		System.out.println("var4 & (var3 - 1) 十进制：" + (var4 & (long)(var3 - 1)));
		System.out.println("var4 & (var3 - 1) 二进制：" + Long.toBinaryString(var4 & (long)(var3 - 1)));

		System.out.println("var4 + var3 十进制：" + (var4 + (long)var3));
		System.out.println("var4 + var3 二进制：" + Long.toBinaryString(var4 + (long)var3));

		System.out.println("var4 + var3 - (var4 & (var3 - 1)) 十进制：" + (var4 + (long)var3 - (var4 & (long)(var3 - 1))));
		System.out.println("var4 + var3 - (var4 & (var3 - 1)) 二进制：" + Long.toBinaryString(var4 + (long)var3 - (var4 & (long)(var3 - 1))));

	}
}
