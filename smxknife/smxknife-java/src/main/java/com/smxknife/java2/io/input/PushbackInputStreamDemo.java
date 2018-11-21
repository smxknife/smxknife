package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 * @author smxknife
 * 2018/11/19
 */
public class PushbackInputStreamDemo {
	public static void main(String[] args) throws IOException {
		String url = "www.baidu.com";

		PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream(url.getBytes()));
		int read = 0;
		System.out.println(pushbackInputStream.available());
		while ((read = pushbackInputStream.read()) != -1) {
			if ('.' == (char) read) {
				pushbackInputStream.unread(read);
				 read = pushbackInputStream.read();
				System.out.println("回退 " + (char) read);
			} else
				System.out.println((char) read);
		}
		System.out.println(pushbackInputStream);
	}
}
