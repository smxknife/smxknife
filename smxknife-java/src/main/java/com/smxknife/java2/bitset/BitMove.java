package com.smxknife.java2.bitset;

/**
 * @author smxknife
 * 2021/7/14
 */
public class BitMove {
	public static void main(String[] args) {
		int num = 5;

		num |= num >>> 1;
		System.out.println(num);
		num |= num >>> 2;
		System.out.println(num);
		num |= num >>> 4;
		System.out.println(num);
		num |= num >>> 8;
		System.out.println(num);
		num |= num >>> 16;
		System.out.println(num);
		System.out.println(num + 1);

		System.out.println(5 % 3);
		System.out.println(5 & num);
	}
}
