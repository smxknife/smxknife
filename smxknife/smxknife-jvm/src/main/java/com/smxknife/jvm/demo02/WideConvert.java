package com.smxknife.jvm.demo02;

public class WideConvert {
	public static void main(String[] args) {
		int m = 15;
		double n = m;
		System.out.println(m == n);

		double i = 15.1;
		int j = (int) i;
		System.out.printf("i = %s, j = %s, i == j : %s4", i, j, i == j);
	}
}
