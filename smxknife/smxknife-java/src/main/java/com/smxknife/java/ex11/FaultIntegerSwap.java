package com.smxknife.java.ex11;

public class FaultIntegerSwap {
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		System.out.printf("a = %s, b = %s\n", a, b);
		swap(a, b);
		System.out.printf("a = %s, b = %s\n", a, b);
	}

	public static void swap(Integer a, Integer b) {
		Integer temp = a;
		a = b;
		b = temp;
	}
}
