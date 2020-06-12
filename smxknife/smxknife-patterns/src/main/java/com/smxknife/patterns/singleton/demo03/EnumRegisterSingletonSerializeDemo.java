package com.smxknife.patterns.singleton.demo03;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @author smxknife
 * 2019/12/26
 */
public class EnumRegisterSingletonSerializeDemo {
	public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		EnumRegisterSingleton instance = EnumRegisterSingleton.INSTANCE;

		try(FileOutputStream fos = new FileOutputStream("./enumRegisterSingleton.ser");
		    ObjectOutputStream oos = new ObjectOutputStream(fos)) {

			oos.writeObject(instance);
			System.out.println("序列化完毕");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try(FileInputStream fis = new FileInputStream("./enumRegisterSingleton.ser");
			ObjectInputStream ois = new ObjectInputStream(fis)) {

			Object o = ois.readObject();
			System.out.println("反序列化完毕");
			System.out.println("原对象：" + instance);
			System.out.println("发序列化对象：" + o);
			System.out.println(instance == o);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
