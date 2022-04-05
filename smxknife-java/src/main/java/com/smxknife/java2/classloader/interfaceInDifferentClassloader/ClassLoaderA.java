package com.smxknife.java2.classloader.interfaceInDifferentClassloader;

import java.io.*;

/**
 * @author smxknife
 * 2019/12/29
 */
public class ClassLoaderA extends ClassLoader {
	/**
	 * 这里为了不让父加载器加载，这里直接重写loadClass
	 * 另外，这里简化加载路径，默认当前
	 * @param name
	 * @return
	 * @throws ClassNotFoundException
	 */
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
