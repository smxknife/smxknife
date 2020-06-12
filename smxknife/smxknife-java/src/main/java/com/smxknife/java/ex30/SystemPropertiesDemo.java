package com.smxknife.java.ex30;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author smxknife
 * 2020/1/8
 */
public class SystemPropertiesDemo {
	public static void main(String[] args) throws IOException {
		String userDir = System.getProperty("user.dir");
		String userHome = System.getProperty("user.home");
		String javaHome = System.getProperty("JAVA_HOME");
		System.out.println(userDir);
		System.out.println(userHome);
		System.out.println(javaHome);
		Properties properties = System.getProperties();
		System.out.println(properties);

		File file = new File(userDir);
		File canonicalFile = file.getCanonicalFile();
		System.out.println(canonicalFile.getCanonicalPath());

		Map<String, String> getenv = System.getenv();
		System.out.println(getenv);
		System.out.println(getenv.get("JAVA_HOME"));
	}
}
