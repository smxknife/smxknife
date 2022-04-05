package com.smxknife.java2.io.output;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/19
 */
public class BufferedOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream, 10);

		System.out.println(byteArrayOutputStream.size());
		String content = "hello world";
		for (int i = 0; i < 20; i++) {
			bufferedOutputStream.write(content.getBytes());
			System.out.println(byteArrayOutputStream.size());
		}
		System.out.println(byteArrayOutputStream.size());
		bufferedOutputStream.close();
	}
}
