package com.smxknife.java.ex20;

import com.smxknife.java.ex20.b.Aa;
import com.smxknife.java.ex20.b.Ab;

/**
 * @author smxknife
 * 2018-12-02
 */
public class A {
	public static void main(String[] args) {
		Aa a = new Aa();
		Ab a1 = (Ab) a;
		a1.output();
	}
}

