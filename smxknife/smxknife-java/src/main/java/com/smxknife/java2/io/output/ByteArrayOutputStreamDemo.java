package com.smxknife.java2.io.output;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/19
 */
public class ByteArrayOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		String str = "hello";
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byteArrayOutputStream.write(str.getBytes());
//		byteArrayOutputStream.flush();
//		byte[] bytes = byteArrayOutputStream.toByteArray();
//		System.out.println(new String(bytes));
		System.out.println(byteArrayOutputStream.toString());
	}
}
