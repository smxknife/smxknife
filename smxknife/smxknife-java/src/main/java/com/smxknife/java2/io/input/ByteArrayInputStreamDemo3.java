package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;

/**
 * @author smxknife
 * 2019-01-30
 */
public class ByteArrayInputStreamDemo3 {
	public static void main(String[] args) {

		String content = "hello_world";

		ByteArrayInputStream bis = new ByteArrayInputStream(content.getBytes());

		byte[] bytes = new byte[content.length() + 5];
		System.out.println(new String(bytes));
		bis.read(bytes, 5, bis.available());
		for (int i = 0; i < bytes.length; i++) {
			System.out.print(" " + (char)bytes[i]);
		}
		System.out.println();
	}
}
