package com.smxknife.java2.externalizable;

import java.io.*;

/**
 * @author smxknife
 * 2018/11/5
 */
public class Animal2SerializableDemo {
	public static void main(String[] args) throws Exception {
		String serializableFile = "./animal2.ser";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		// 为了节省博文篇幅，直接简单粗暴的处理，不必纠结细节
		fos = new FileOutputStream(new File(serializableFile));
		oos = new ObjectOutputStream(fos);
		Animal2 animal = new Animal2("emo2", 2);
		oos.writeObject(animal);
		oos.close();


		fis = new FileInputStream(new File(serializableFile));
		ois = new ObjectInputStream(fis);
		Animal2 animal2 = (Animal2) ois.readObject();
		System.out.println(animal2);
		fis.close();
	}
}
