package com.smxknife.java2.classloader.class_geli;

/**
 * @author smxknife
 * 2020/12/30
 */
public class TestA {
	public static void main(String[] args) {
		test1();
	}

	private static void test1() {
		TestA testA = new TestA();
		testA.hello();
	}

	public void hello() {
		System.out.println("TestA " + this.getClass().getClassLoader());
		TestB testB = new TestB();
		testB.hello();
	}
}
