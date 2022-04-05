package com.smxknife.java2.jvm.error;

/**
 * 模拟java虚拟机栈抛出StackOverflowError，通过不断的递归调用方法，增加调用栈栈的深度
 * @author smxknife
 * 2019-06-14
 */
public class JavaStackStackOverflowError {
	public static void main(String[] args) {
		method(0);
	}

	static void method(int i) {
		System.out.println("depth = " + i);
		method(++i);
	}
}
