package com.smxknife.java2.io.reader;

import java.io.IOException;
import java.io.StringReader;

/**
 * @author smxknife
 * 2018/11/20
 */
public class StringReaderDemo {
	public static void main(String[] args) throws IOException {
		StringReader stringReader = new StringReader("hello-world!");
		char[] chars = new char[5];
		stringReader.read(chars);
		System.out.println(new String(chars));
	}
}
