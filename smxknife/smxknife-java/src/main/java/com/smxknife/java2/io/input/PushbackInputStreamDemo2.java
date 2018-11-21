package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author smxknife
 * 2018/11/19
 */
public class PushbackInputStreamDemo2 {
	public static void main(String[] args) throws IOException {
		String url = "www.baidu.com";

		PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream(url.getBytes()));
		int read = 0;
		while ((read = pushbackInputStream.read()) != -1) {
			if ('w' == read) {
				pushbackInputStream.unread('W');
			}
			System.out.print((char) read);
		}
	}
}
