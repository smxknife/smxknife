package com.smxknife.java.ex1;

public class OddDemo2 {
	public static void main(String[] args) {
		for (int i = -10; i < 10; i++) {
			mod2(i);
		}
	}

	public static void mod2(int i) {
		System.out.println(i + " % 2 = " + (i % 2));
	}
}
