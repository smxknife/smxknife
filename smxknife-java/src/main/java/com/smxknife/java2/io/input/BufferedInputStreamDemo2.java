package com.smxknife.java2.io.input;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-01-31
 */
public class BufferedInputStreamDemo2 {
	public static void main(String[] args) {
		ByteArrayInputStream bis = new ByteArrayInputStream("hello_world".getBytes());
		BufferedInputStream bufferedIs = new BufferedInputStream(bis);
		try {
			System.out.println(bufferedIs.available());
			System.out.println(bufferedIs.markSupported());
			bufferedIs.mark(10);
			System.out.println((char) bufferedIs.read());
			System.out.println((char) bufferedIs.read());
			bufferedIs.reset();
			System.out.println((char) bufferedIs.read());
			System.out.println((char) bufferedIs.read());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
