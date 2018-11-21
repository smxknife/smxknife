package com.smxknife.java2.io.output;

import java.io.*;

/**
 * @author smxknife
 * 2018/11/19
 */
public class DataOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

		dataOutputStream.writeUTF("hello");
		dataOutputStream.writeBoolean(true);
		dataOutputStream.writeChar('0');
		dataOutputStream.writeDouble(10.0);
		dataOutputStream.writeDouble(102.0);
		System.out.println(byteArrayOutputStream.toString());

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
		DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
		System.out.println(dataInputStream.readUTF());
		System.out.println(dataInputStream.readChar());
		System.out.println(dataInputStream.readBoolean());
		System.out.println(dataInputStream.readDouble());
		System.out.println(dataInputStream.readDouble());
	}
}
