package com.smxknife.java2.classloader.hotswap;

import java.io.File;
import java.io.FileInputStream;
import java.text.MessageFormat;

/**
 * @author smxknife
 * 2019/10/31
 */
public class DynamicClassLoader extends ClassLoader {

	// 文件最后修改时间
	private long lastModified;

	// 加载class的path
	private String classPath;

	private boolean isClassModified(String name) {
		File file = getFile(name);
		if (file.lastModified() > lastModified) {
			return true;
		}
		return false;
	}

	private File getFile(String name) {
		String simpleName = "";
		String packageName = "";
		if (name.indexOf(".") != -1) {
			simpleName = name.substring(name.lastIndexOf(".") + 1);
			packageName = name.substring(0, name.lastIndexOf(".")).replaceAll("[.]", "/");
		} else {
			simpleName = name;
		}
		File file = new File(MessageFormat.format("{0}/{1}/{2}.class", classPath, packageName, simpleName));
		return file;
	}

	private byte[] getBytes(String name) {
		byte[] buffer = null;

		File file = getFile(name);
		try (FileInputStream in = new FileInputStream(file)) {

			lastModified = file.lastModified();
			buffer = new byte[in.available()];
			in.read(buffer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer;
	}

	public Class<?> loadClass(String classPath, String name) throws ClassNotFoundException {
		this.classPath = classPath;
		if (isClassModified(name)) {
			return findClass(name);
		}
		return null;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		byte[] byteCode = getBytes(name);
		return defineClass(null, byteCode, 0, byteCode.length);
	}
}
