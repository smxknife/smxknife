package com.smxknife.springboot.v2.learn.common;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/2/6
 */
public class C {
	private A a;

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public void test() {
		if (Objects.isNull(a)) {
			System.out.println("A is null");
		} else {
			a.test();
		}
	}
}
