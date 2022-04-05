package com.smxknife.java2.classloader.class_geli;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/12/30
 */
public class GeliClassLoader_loadClass extends ClassLoader {
	private Map<String, String> classPathMap = new HashMap<>();
	private ClassLoader jdkClassLoader;

	public GeliClassLoader_loadClass(ClassLoader jdkClassLoader) {
		this.jdkClassLoader = jdkClassLoader;
		classPathMap.put("com.smxknife.java2.classloader.class_geli.TestA", "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes/com/smxknife/java2/classloader/class_geli/TestA.class");
		classPathMap.put("com.smxknife.java2.classloader.class_geli.TestB", "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes/com/smxknife/java2/classloader/class_geli/TestB.class");
	}

	// 这里重写findClass会已双亲委派的形式进行查找类，不符合隔离的要求，所以只能重写loadClass
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class result = null;
		try {
			result = jdkClassLoader.loadClass(name);
		} catch (Exception e) {

		}
		if (Objects.nonNull(result)) {
			return result;
		}

		String classPath = classPathMap.get(name);
		try {
			byte[] bytes = Files.readAllBytes(Paths.get(classPath));
			if (Objects.isNull(bytes) || bytes.length == 0) {
				throw new ClassNotFoundException(name);
			}
			return defineClass(bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}
