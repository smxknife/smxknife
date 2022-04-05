package com.smxknife.java2.io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @author smxknife
 * 2018/11/20
 */
public class RandomAccessFileDemo2 {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/FileTest2.txt";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");

		for (int i = 0; i < 5; i++) {
			randomAccessFile.seek(randomAccessFile.length());
			randomAccessFile.writeUTF("世界");
			randomAccessFile.seek(0);
			randomAccessFile.writeUTF("你好" + i);
		}

	}
}
