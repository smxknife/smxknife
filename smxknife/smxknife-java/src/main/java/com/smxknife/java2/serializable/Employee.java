package com.smxknife.java2.serializable;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author smxknife
 * 2018/11/2
 */
public class Employee extends People implements Serializable {
	private static final long serialVersionUID = 1L;

	public String name;
	public String address;
	public transient int SSN;
	public int number;
	public static String staticAttr;
	public void mailCheck()
	{
		System.out.println("Mailing a check to " + name
				+ " " + address);
	}

	private void writeObject(ObjectOutputStream oos) {
		try {
			ObjectOutputStream.PutField putField = oos.putFields();
			System.out.println("is serializing");
			System.out.println("name = " + name);
			name = name + "_suffix";
			putField.put("name", name);
			oos.writeFields();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readObject(ObjectInputStream ois) {
		try {
			ois.readObject();
//			ObjectInputStream.GetField getField = ois.readFields();
//			System.out.println("is deserializing");
//			System.out.println(getField.get("name", ""));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
