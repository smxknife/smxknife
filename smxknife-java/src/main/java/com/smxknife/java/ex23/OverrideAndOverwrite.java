package com.smxknife.java.ex23;

import java.util.HashMap;

/**
 * @author smxknife
 * 2019-01-24
 */
public class OverrideAndOverwrite {
	public static void main(String[] args) {
		GroupA a = new GroupA();
		a.group(new HashMap());

		GroupA a1 = new GroupAA();
		a1.group(new HashMap());

		GroupB b = new GroupB();
		b.group(new HashMap());

		GroupB b1 = new GroupBB();
		b1.group(null);
		b1.group(new HashMap());
	}
}
