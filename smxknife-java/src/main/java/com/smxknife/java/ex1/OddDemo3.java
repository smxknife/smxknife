package com.smxknife.java.ex1;

public class OddDemo3 {
	public static void main(String[] args) {
		for (int i = -10; i < 10; i++) {
			output(i);
		}
	}

	public static void output(int i) {
		System.out.print(i + " isOdd : ");
		System.out.print(isOdd(i));
		System.out.print(" , isOdd2 : ");
		System.out.println(isOdd2(i));
	}

	public static boolean isOdd(int i) {
		return i % 2 != 0;
	}

	public static boolean isOdd2(int i) {
		return (i & 1) == 1;
	}
}
