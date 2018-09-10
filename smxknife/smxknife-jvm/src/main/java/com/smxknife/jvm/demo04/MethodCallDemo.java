package com.smxknife.jvm.demo04;

public class MethodCallDemo {
	public static void main(String[] args) {
		MethodCallDemo call = new MethodCallDemo();
		call.m1();
	}

	public int m1() {
		return m2();
	}

	public int m2() {
		return 1;
	}
}
