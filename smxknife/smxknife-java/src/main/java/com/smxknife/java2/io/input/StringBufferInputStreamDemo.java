package com.smxknife.java2.io.input;

import java.io.IOException;
import java.io.StringBufferInputStream;

/**
 * @author smxknife
 * 2018/11/12
 */
public class StringBufferInputStreamDemo {
	public static void main(String[] args) throws IOException {
		String content = "hello world";
		StringBufferInputStream stringBufferInputStream = new StringBufferInputStream(content);
		System.out.println(stringBufferInputStream.available());
		int read = stringBufferInputStream.read();
		System.out.println(read);
		System.out.println((char) read);
		System.out.println(stringBufferInputStream.available());

		byte[] bytes = new byte[stringBufferInputStream.available()];
		stringBufferInputStream.read(bytes);
		System.out.println(stringBufferInputStream.available());
		System.out.println(bytes);

		for (int i = 0; i < bytes.length; i++) {
			System.out.print(bytes[i] + " ");
			System.out.println((char) bytes[i]);
		}
	}
}
