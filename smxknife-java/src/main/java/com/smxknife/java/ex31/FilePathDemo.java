package com.smxknife.java.ex31;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2020/8/20
 */
public class FilePathDemo {
	public static void main(String[] args) {
		System.getProperty("aaaaaa");
		Stream.of(args).forEach(System.out::println);

		Path common = Paths.get("common");
		System.out.println(common.isAbsolute());
		System.out.println(Paths.get("User/../").isAbsolute());
		System.out.println(Files.isDirectory(common));
	}
}
