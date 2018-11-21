package com.smxknife.java2.io.input;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/19
 */
public class DataInputStreamDemo {
	public static void main(String[] args) throws IOException {
		String content = "hello";
		System.out.println(content.getBytes().length);
		System.out.println(content.getBytes("UTF-8").length);
		DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(content.getBytes("utf8")));

		// EOFException 读到文件结尾了，这个需要配置DataOutputStream
		// System.out.println(dataInputStream.readUTF());

		// 读到缓存
		byte[] bytes = new byte[content.getBytes().length];
		System.out.println(dataInputStream.read(bytes));
		System.out.println(new String(bytes, 0, bytes.length));

	}
}
