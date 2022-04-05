package com.smxknife.java2._lambda.custom;

/**
 * @author smxknife
 * 2020/8/12
 */
public class _Main {
	public static void main(String[] args) {
		Lambda01 lambda01 = () -> {
			System.out.println("xxxx");
		};

		lambda01Test(lambda01);
	}

	public static void lambda01Test(Lambda01 lambda01) {
		lambda01.run();
	}
}
