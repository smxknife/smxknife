package com.smxknife.java2.jvm.bytecode;

import java.io.IOException;

/**
 * @author smxknife
 * 2021/7/19
 */
public class HelloMain {
	public static void main(String[] args) {
		try {
			foo();
		} catch (NullPointerException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}

		try {
			foo();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void foo() throws IOException {}
}
