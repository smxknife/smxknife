package com.smxknife.java2.io.file;

import java.io.File;
import java.util.Arrays;

/**
 * @author smxknife
 * 2018/11/12
 */
public class FileDemo {
	public static void main(String[] args) {
		File path = new File(".");
		String[] list = path.list();
		System.out.println(Arrays.asList(list));
		System.out.println("=====================");

		String[] srcs = path.list(new DirFilter("src"));
		System.out.println(Arrays.asList(srcs));
		System.out.println("=====================");

		File[] files = path.listFiles(new MyFileFilter());
		System.out.println(Arrays.asList(files));
		System.out.println("=====================");


	}
}
