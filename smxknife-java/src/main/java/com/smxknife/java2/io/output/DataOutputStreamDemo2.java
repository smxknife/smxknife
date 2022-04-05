package com.smxknife.java2.io.output;

import java.io.*;

/**
 * @author smxknife
 * 2018/11/19
 */
public class DataOutputStreamDemo2 {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/output/Test.txt";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		FileOutputStream fileOutputStream = new FileOutputStream(file);

		DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);

		dataOutputStream.writeDouble(10.1);
		dataOutputStream.writeDouble(11);
		dataOutputStream.writeChar(9);
		dataOutputStream.writeBoolean(true);
		dataOutputStream.writeBoolean(false);
		dataOutputStream.writeUTF("hello");
		dataOutputStream.writeUTF("world");
		dataOutputStream.writeBytes("test");
		dataOutputStream.flush();
		dataOutputStream.close();

		FileInputStream fileInputStream = new FileInputStream(file);
		DataInputStream dataInputStream = new DataInputStream(fileInputStream);

		System.out.println(dataInputStream.readDouble());
		System.out.println(dataInputStream.readDouble());
		System.out.println(dataInputStream.readDouble());
		System.out.println(dataInputStream.readDouble());

		System.out.println(dataInputStream.readChar());
		System.out.println(dataInputStream.readChar());
		System.out.println(dataInputStream.readChar());
		System.out.println(dataInputStream.readBoolean());
		System.out.println(dataInputStream.readBoolean());
		System.out.println(dataInputStream.readBoolean());

		System.out.println(dataInputStream.readUTF());
		System.out.println(dataInputStream.readUTF());
		System.out.println(dataInputStream.readUTF());

		System.out.println(dataInputStream.readByte());
		dataInputStream.close();
	}
}
