package com.smxknife.bytebuddy.common;

import net.bytebuddy.implementation.bind.annotation.BindingPriority;

/**
 * @author smxknife
 * 2020/10/20
 */
public class Bar2 {
	@BindingPriority(3) // TODO 值越大优先级越高
	public static String sayHelloBar1() {
		return "Hello in Bar 1 !";
	}

	@BindingPriority(2)
	public static String sayHelloBar2() {
		return "Hello in Bar 2 !";
	}
}
