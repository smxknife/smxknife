package com.smxknife.java2.io.reader;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.StringReader;

/**
 * @author smxknife
 * 2018/11/20
 */
public class PushbackReaderDemo {
	public static void main(String[] args) throws IOException {
		StringReader stringReader = new StringReader(":hello");
		PushbackReader pushbackReader = new PushbackReader(stringReader, 10);

		int len = 0;
		while ((len = pushbackReader.read()) != -1) {
			System.out.print((char) len);
			if (':' == len)
				pushbackReader.unread("-world-".toCharArray());
		}

	}
}
