package com.smxknife.java2.classloader.interfaceInDifferentClassloader;

import java.io.*;

/**
 * @author smxknife
 * 2019/12/29
 */
public class ClassLoaderB extends ClassLoader {

	@Override
	public Class<?> findClass(String name) throws ClassNotFoundException {
		String path = this.getClass().getResource("").getPath();
		String packageName = this.getClass().getPackage().getName();

		File classFile = new File(path, name + ".class");

		try(FileInputStream fis = new FileInputStream(classFile);
		    ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

			int len = 0;
			while ((len = fis.read()) != -1) {
				bos.write(len);
			}
			byte[] bytes = bos.toByteArray();
			return defineClass(packageName + "." + name, bytes, 0, bytes.length);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return super.findClass(name);
	}
}
