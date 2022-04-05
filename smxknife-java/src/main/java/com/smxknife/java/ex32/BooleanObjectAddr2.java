package com.smxknife.java.ex32;

import java.io.IOException;

/**
 * @author smxknife
 * 2021/5/6
 */
public class BooleanObjectAddr2 {
	public static void main(String[] args) throws IOException {
		boolean b1 = true;
		boolean b2 = true;

		System.out.println(System.identityHashCode(b1));
		System.out.println(System.identityHashCode(b2));

		new Thread(() -> {
			boolean b3 = true;
			System.out.println(System.identityHashCode(b3));
		}).start();

		System.in.read();
	}

}
