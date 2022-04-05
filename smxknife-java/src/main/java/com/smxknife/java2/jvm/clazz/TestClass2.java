package com.smxknife.java2.jvm.clazz;

/**
 * @author smxknife
 * 2019-07-01
 */
public class TestClass2 {

	public int inc() {
		long a = 1L;
		float b = a;


		int x;
		try {
			x = 1;
			System.out.println("try" + x);
						throw new RuntimeException();
//			return x;
		} catch (Exception e) {
			x = 2;
			System.out.println("catch" + x);
			return x;
		} finally {
			x = 3;
			System.out.println("finally" + x);
//			return x;
		}
	}

	public static void main(String[] args) {
		TestClass2 class2 = new TestClass2();
		System.out.println(class2.inc());
	}

}
