package com.smxknife.java2.io.input;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author smxknife
 * 2018/11/14
 */
public class PipedInputStreamSingleThreadDemo {
	public static void main(String[] args) throws IOException {
		PipedOutputStream outputStream = new PipedOutputStream();
		PipedInputStream inputStream = new PipedInputStream();

		inputStream.connect(outputStream);
//		outputStream.connect(inputStream);
		outputStream.write("hellohello".getBytes());
		byte[] bytes = new byte[1024];
		int read = inputStream.read(bytes);
		System.out.println(new String(bytes, 0, read));

		outputStream.write("world".getBytes());

		read = inputStream.read(bytes);
		System.out.println(new String(bytes, 0, read));


	}
}
