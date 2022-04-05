package com.smxknife.java2.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author smxknife
 * 2018/11/20
 */
public class RandomAccessFileDemo {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/FileTest.txt";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
		randomAccessFile.seek(randomAccessFile.length());
		randomAccessFile.write(1);
		randomAccessFile.writeDouble(10.1);
		randomAccessFile.writeBoolean(true);
		randomAccessFile.writeChars("abcd");
		randomAccessFile.writeUTF("世界");

	}
}
