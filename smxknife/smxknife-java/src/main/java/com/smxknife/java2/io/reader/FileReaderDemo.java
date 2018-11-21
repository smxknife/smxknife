package com.smxknife.java2.io.reader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/20
 */
public class FileReaderDemo {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/input/FileInputStreamDemo.java";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		FileReader fileReader = new FileReader(file);

		char[] chars = new char[10];
		int len = 0;
		while ((len = fileReader.read(chars)) != -1) {
			System.out.println(new String(chars));
		}
	}
}
