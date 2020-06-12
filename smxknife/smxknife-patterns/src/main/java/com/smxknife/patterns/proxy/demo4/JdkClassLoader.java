package com.smxknife.patterns.proxy.demo4;

import java.io.*;
import java.util.Objects;

/**
 * @author smxknife
 * 2019/12/27
 */
public class JdkClassLoader extends ClassLoader {

	private File classPathFile;

	public JdkClassLoader() {
		String classPath = JdkClassLoader.class.getResource("").getPath();
		this.classPathFile = new File(classPath);
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String className = JdkClassLoader.class.getPackage().getName() + "." + name;
		if (Objects.nonNull(classPathFile)) {
			File classFile = new File(classPathFile, name.replaceAll("\\.", "/") + ".class");
			if (classFile.exists()) {

				try(FileInputStream fis = new FileInputStream(classFile);
				    ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

					byte[] buff = new byte[1024];
					int len;
					while ((len = fis.read(buff)) != -1) {
						bos.write(buff, 0, len);
					}
					return defineClass(className, bos.toByteArray(), 0, bos.size());
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
