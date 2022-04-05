package com.smxknife.java2.bitset;

/**
 * @author smxknife
 * 2020/4/29
 */
public class Minus {
	public static void main(String[] args) {
		int i = 15, j = 10;
		System.out.println(Integer.toBinaryString(i));
		System.out.println(Integer.toBinaryString(j));

		System.out.println(Integer.toBinaryString(i - j));
		System.out.println("结论：二进制减法和十进制减法是一样的，不够从前一位借1");

	}
}
