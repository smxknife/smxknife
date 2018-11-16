// 测试文本
package com.smxknife.java2.io.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/12
 */
public class FileInputStreamDemo {
	public static void main(String[] args) throws IOException {
		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/input/FileInputStreamDemo.java";
		File file = new File(filePath);
		System.out.println(file.getAbsolutePath());
		FileInputStream fileInputStream = new FileInputStream(filePath);
		System.out.println(fileInputStream.available());
//		int read = fileInputStream.read();
//		System.out.print(read + " ");
//		System.out.println((char) read);
//		System.out.println(fileInputStream.available());

		byte[] bytes = new byte[6];
//		fileInputStream.read(bytes);
//		System.out.println(bytes);
//		System.out.println(fileInputStream.available());

		int len = 0;
		while ((len = fileInputStream.read(bytes)) != -1 ) {
			System.out.println(new String(bytes, 0, len));
		}


	}
}
