package com.smxknife.java2.io.input;

import java.io.*;

/**
 * @author smxknife
 * 2018/11/19
 */
public class DataInputStreamWithDataOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		String content = "hello";

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
		dataOutputStream.writeUTF(content);
		dataOutputStream.flush();

		// 从这个输出可以看到前面会插入两个字符
//		for (int i = 0; i < byteArrayOutputStream.toByteArray().length; i++) {
//			System.out.println((char) byteArrayOutputStream.toByteArray()[i]);
//		}

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
		System.out.println(dataInputStream.readUTF());
	}
}
