package com.smxknife.java2.classloader.demo01;

import java.io.*;

/**
 * @author smxknife
 * 2019/10/16
 */
public class DemoClassLoader_findClass extends ClassLoader {

	public static void main(String[] args) throws ClassNotFoundException {
		DemoClassLoader_findClass loader = new DemoClassLoader_findClass("/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes/com/smxknife/java2/classloader/demo01");
		loader.findClass("com.smxknife.java2.classloader.demo01.LoadObject");
	}

	private String libPath;

	public DemoClassLoader_findClass(String libPath) {
		this.libPath = libPath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = getFileName(name);
		File file = new File(libPath, fileName);

		try (FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

			int len = 0;
			while ((len = fis.read()) != -1) {
				bos.write(len);
			}

			byte[] data = bos.toByteArray();
			return defineClass(name, data, 0, data.length);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	private String getFileName(String name) {
		int index = name.lastIndexOf('.');
		if (index == -1) {
			return name + ".class";
		} else {
			return name.substring(index + 1) + ".class";
		}

	}

}
