package com.smxknife.java2.io.input;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/15
 */
public class BufferedInputStreamDemo {
	public static void main(String[] args) throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello".getBytes());
		BufferedInputStream bufferedInputStream = new BufferedInputStream(byteArrayInputStream);
		System.out.println(bufferedInputStream.read());

	}
}
