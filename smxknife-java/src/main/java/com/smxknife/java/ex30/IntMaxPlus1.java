package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2020/1/20
 */
public class IntMaxPlus1 {
	public static void main(String[] args) {
		int i = Integer.MAX_VALUE + 1;
		System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Math.pow(2, 31));
		System.out.println(i);
		System.out.println(1 << 4);
		System.out.println(1 >> 4);
	}
}
