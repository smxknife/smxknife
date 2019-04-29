package com.smxknife.java2.io.output;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author smxknife
 * 2019-02-13
 */
public class FileOutputStreamDemo2 {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos = new FileOutputStream(FileDescriptor.out);
		fos.write('V');
		fos.close();
		System.out.println();
	}
}
