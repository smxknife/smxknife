package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;

/**
 * @author smxknife
 * 2019-01-30
 */
public class ByteArrayInputStreamDemo2 {
	public static void main(String[] args) {
		byte[] bytes = new byte[10];
		ByteArrayInputStream bis = new ByteArrayInputStream(bytes);

		System.out.println(bis.available());
		System.out.println(bis.markSupported());
//		for (int i = 0; i < bis.available(); i++) {
//			System.out.println(bis.read());
//		}

		int i = -1;
//		while ((i = bis.read()) != -1) {
//			System.out.println(i);
//		}

		bis = new ByteArrayInputStream("abc_defg".getBytes());
		System.out.println(bis.available());

		System.out.println((char) bis.read());
		System.out.println(bis.available());
		bis.skip(3);
		bis.mark(2);
		System.out.println((char) bis.read());
		System.out.println((char) bis.read());

		System.out.println("---------------");
		bis.reset();
		System.out.println((char) bis.read());


	}
}
