package com.smxknife.jvm.demo03;

public class CompareDemo {
	public static void main(String[] args) {
//		System.out.println(Double.compare(1.0, 2.0));
//		System.out.println(Double.compare(2.0, 1.0));
//		System.out.println(Double.compare(Double.NaN, 1.0));
//		System.out.println(Double.compare(1.0, Double.NaN));
//		System.out.println(Double.compare(Double.NaN, Double.NaN));
//		System.out.println(Double.NaN == Double.NaN);

		Object a1 = Double.compare(Double.NaN, 1.0);
		Object a2 = Double.compare(Double.NaN, Double.NaN);
		Object a3 = Double.NaN == Double.NaN;


	}
}
