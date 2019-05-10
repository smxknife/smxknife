package com.smxknife.java.ex27;

/**
 * @author smxknife
 * 2019-05-07
 */
public class Demo2 {
	public static void main(String[] args) {
		String s = "";
		for (int i = 10; i > 0; i /= 2) {
			System.out.println(i);
			s = (i % 2) + s;
		}
		System.out.println(s);
	}
}
