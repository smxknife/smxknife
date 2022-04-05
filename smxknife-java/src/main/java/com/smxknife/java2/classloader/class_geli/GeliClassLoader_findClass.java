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
public class GeliClassLoader_findClass extends ClassLoader {
	private Map<String, String> classPathMap = new HashMap<>();

	public GeliClassLoader_findClass() {
		classPathMap.put("com.smxknife.java2.classloader.class_geli.TestA", "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes/com/smxknife/java2/classloader/class_geli/TestA.class");
		classPathMap.put("com.smxknife.java2.classloader.class_geli.TestB", "/Users/ShaoYun/local/workstation/programs/smxknife/smxknife/smxknife-java/target/classes/com/smxknife/java2/classloader/class_geli/TestB.class");
	}

	// 这里重写findClass会已双亲委派的形式进行查找类，不符合隔离的要求，所以只能重写loadClass
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
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
