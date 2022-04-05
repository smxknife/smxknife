package com.smxknife.java2.externalizable;

import java.io.*;

/**
 * @author smxknife
 * 2018/11/5
 */
public class AnimalSimpleSerializableDemo {
	public static void main(String[] args) throws Exception {
		String serializableFile = "./animal.ser";
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		// 为了节省博文篇幅，直接简单粗暴的处理，不必纠结细节
		fos = new FileOutputStream(new File(serializableFile));
		oos = new ObjectOutputStream(fos);
		AnimalSample animal = new AnimalSample();
		animal.age = 1;
		animal.name = "emo";
		oos.writeObject(animal);
		oos.close();

		fis = new FileInputStream(new File(serializableFile));
		ois = new ObjectInputStream(fis);
		AnimalSample animal2 = (AnimalSample) ois.readObject();
		System.out.println(animal2);
		fis.close();
	}
}
