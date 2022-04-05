package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/12
 */
public class ByteArrayInputStreamDemo {
	public static void main(String[] args) throws IOException {
		String content = "hello world";
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(content.getBytes());
		System.out.println("==== available : " + byteArrayInputStream.available());

		int read = byteArrayInputStream.read();
		System.out.println(read);
		System.out.println("==== available : " + byteArrayInputStream.available());
		System.out.println("------------------------");

		byte[] bytes = new byte[content.getBytes().length];
		byteArrayInputStream.read(bytes);
		System.out.println(bytes);

		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i] + " ");
			System.out.println((char) bytes[i]);
			System.out.println("==== available : " + byteArrayInputStream.available());
		}
		System.out.println("------------------------");

		int read1 = byteArrayInputStream.read();
		System.out.println(read1);
		System.out.println("==== available : " + byteArrayInputStream.available());
		System.out.println("------------------------");


	}
}
