package com.smxknife.java2.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/19
 */
public class FileOutputStreamDemo {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/output/Test.txt";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		FileOutputStream fileOutputStream = new FileOutputStream(file);
		fileOutputStream.write("你好，FileOutputStream".getBytes());
		fileOutputStream.flush();
		fileOutputStream.close();
	}
}
