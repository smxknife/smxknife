package com.smxknife.jvm.demo02;

/**
 * 简单的javap命令来查看编译器编译的结果
 */
public class CompilerSample {
	public static void main(String[] args) {
		CompilerSample sample = new CompilerSample();
		sample.spin(99999);

		staticTest(100000000);

		non("no");

		defaultFun();

		sample.doubleSpin();
	}

	private static void non(String nothing) {
		String temp = "";
		double num = 1;

		for (int i = 0; i < 10; i++) {

		}
	}

	protected static void staticTest(int param) {
		int i;

		for (i = 0; i < 10; i++) {

		}
	}

	static void defaultFun() {}

	public void spin(long param) {
		int i;

		for (i = 0; i < 10; i++) {

		}
	}

	public void doubleSpin() {
		for (double i = 0; i < 100.0; i++) {

		}
	}
}
