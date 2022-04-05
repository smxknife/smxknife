package com.smxknife.java.ex4.innerclosure;

public class MyIncrement {
	public void increment() {
		System.out.println("Other operation");
	}

	static void f(MyIncrement mi) {
		mi.increment();
	}
}
