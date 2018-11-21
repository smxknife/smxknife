package com.smxknife.java2.io.writer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/20
 */
public class FileWriterDemo {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/writer/Test.txt";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());

		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write("测试");
		fileWriter.append("aaaaa");
		fileWriter.flush();
		fileWriter.close();
	}
}
