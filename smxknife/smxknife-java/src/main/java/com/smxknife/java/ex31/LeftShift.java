package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/4/23
 */
public class LeftShift {
	public static void main(String[] args) {
		int state = -1;
		System.out.println(Integer.toBinaryString(-1));
		System.out.println(state = state << 29);
		System.out.println(Integer.toBinaryString(state));
		System.out.println(Integer.toBinaryString((1 << 29) ));
		System.out.println(Integer.toBinaryString((1 << 29) - 1));
	}
}
