package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author smxknife
 * 2018/11/19
 */
public class PushbackInputStreamDemo3 {
	public static void main(String[] args) throws IOException {
		String url = "www.baidu.com";

		PushbackInputStream pushbackInputStream =
				new PushbackInputStream(new ByteArrayInputStream(url.getBytes()), url.getBytes().length);
		int read = 0;
		byte[] bytes = new byte[3];
		while ((read = pushbackInputStream.read(bytes)) != -1) {
//			System.out.println("===============");
//			System.out.println(bytes.length);
//			System.out.println(read);
//			System.out.println((char) read);
			String temp = new String(bytes);
			if ("www".equals(temp)) {
				pushbackInputStream.unread("WWW".getBytes());
			}
			System.out.print(new String(bytes));
		}
	}
}
