package com.smxknife.patterns.singleton.demo02;

import java.io.*;

/**
 * @author smxknife
 * 2019/12/26
 */
public class LazyInitializationWithSerializeDemo {
	public static void main(String[] args) {
		Mode_LazyInitialization instance = Mode_LazyInitialization.getInstance();

		serialize(instance, "./lazySingleton.ser");
		Object o = deserialize("./lazySingleton.ser");
		System.out.println("原对象：" + instance);
		System.out.println("反序列化对象：" + o);
		System.out.println(instance == o);
		System.out.println("这里证明，反序列化是可以破坏单例");
		System.out.println("----------------------------------");

		Mode_LazyInitializationCanPreventSerialize mode = Mode_LazyInitializationCanPreventSerialize.getInstance();
		serialize(mode, "./singleton2.ser");
		Object o1 = deserialize("./singleton2.ser");
		System.out.println("原对象：" + mode);
		System.out.println("反序列化对象：" + o1);
		System.out.println(mode == o1);
		System.out.println("这里已经解决了反序列化，但是这里真的是完美的方案吗？" +
				"如果看过源码会发现，这里虽然最后返回的是同一个对象，但是在反序列化过程中其实是创建了不同的对象，只是在最后重新赋值了而已" +
				"如果在大量反序列化会产生太多垃圾，所以这不是一个很好的方案" +
				"更好的方案，请看demo03（注册式单例）");
	}

	private static void serialize(Object mode, String fileName) {
		try(FileOutputStream fos = new FileOutputStream(fileName);
		    ObjectOutputStream oos = new ObjectOutputStream(fos)) {
			oos.writeObject(mode);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("序列化完毕了");
	}

	private static Object deserialize(String fileName) {
		try(FileInputStream fis = new FileInputStream(fileName);
		    ObjectInputStream ois = new ObjectInputStream(fis)) {
			Object o = ois.readObject();
			System.out.println("反序列化完毕");
			return o;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}


}
