package com.smxknife.java.ex1;

public class OddDemo1 {
	public static void main(String[] args) {

		for (int i = -10; i < 10; i++) {
			output(i);
		}

	}

	public static void output(int i) {
		System.out.print(i + " isOdd : ");
		System.out.print(isOdd(i));
		System.out.print(" , isEven : ");
		System.out.println(isEven(i));
	}

	public static boolean isOdd(int i) {
		return i % 2 == 1;
	}

	public static boolean isEven(int i) {
		return i % 2 == 0;
	}
}
