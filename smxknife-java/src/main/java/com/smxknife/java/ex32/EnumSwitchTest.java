package com.smxknife.java.ex32;

/**
 * @author smxknife
 * 2021/1/14
 */
public class EnumSwitchTest {
	public static void main(String[] args) {
		E e = E.A;
		switch (e) {
			case A:
				System.out.println();
				break;
			case C:
				System.out.println();
				break;
			case B:
				System.out.println();
				break;
			default:
				break;
		}
	}

	enum E {
		A, B, C
	}
}
