package com.smxknife.java2._static;

/**
 * @author smxknife
 * 2021/5/6
 */
public class Main {
	public static void main(String[] args) {
		final Main main = new Main();
		StaticClass test = main.test();
		if (test == null) {
			System.out.println("call constructor...");
			test = new StaticClass();
		} else {
			System.out.println("...");
		}
	}

	private StaticClass test() {
		StaticClass staticClass = null;
		return staticClass;
	}
}
