package com.smxknife.java2.io.input;

import java.io.*;

/**
 * @author smxknife
 * 2019-01-30
 */
public class FileInputStreamDemo2 {
	public static void main(String[] args) {
		File path = new File("");
		System.out.println(path.getAbsolutePath());
		String filePath = path.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/input/FileInputStreamDemo.java";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
			System.out.println(fis.available());

			FileDescriptor fd = fis.getFD();
			System.out.println(fis.markSupported());

//			int bytes = -1;
//			fis.skip(4);
//			while ((bytes = fis.read()) != -1) {
//				System.out.println(bytes + " ---- " + (char) bytes);
//			}

			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			System.out.println(new String(bytes));


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
