package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.SequenceInputStream;

/**
 * @author smxknife
 * 2018/11/15
 */
public class SequenceInputStreamDemo2 {
	public static void main(String[] args) throws IOException {
		ByteArrayInputStream hello = new ByteArrayInputStream("hello".getBytes());
		ByteArrayInputStream world = new ByteArrayInputStream("world".getBytes());
		SequenceInputStream sequence = new SequenceInputStream(hello, world);

		byte[] bytes = new byte[1024];
		sequence.read(bytes, 0, bytes.length);
		System.out.println(new String(bytes));

//		int len = 0;
//		while ((len = sequence.read()) != -1) {
//			System.out.print((char) len);
//		}
	}
}
