package com.smxknife.java.ex11;

public class IntParam {
	public static void main(String[] args) {
		int a = 1;
		test(a);
		IntParam param = new IntParam();
		testObj(param);
		String str = "hello";
		testStr(str);
	}

	private static void test(int a) {
		int c = a;
	}

	private static void testObj(IntParam param) {

	}

	private static void testStr(String str) {

	}
}
