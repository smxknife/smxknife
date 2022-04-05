package com.smxknife.java2.serializable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * @author smxknife
 * 2018/11/2
 */
public class EmployeeDeserializeDemo {
	public static void main(String[] args) {
		Employee e = null;

		try {
			FileInputStream fis = new FileInputStream("./employee.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			e = (Employee) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			System.out.println("employee class not found");
			e1.printStackTrace();
		}

		System.out.println("Deserialized Employee...");
		System.out.println("Name: " + e.name);
		System.out.println("Address: " + e.address);
		System.out.println("SSN: " + e.SSN);
		System.out.println("Number: " + e.number);
		System.out.println("StaticAttr: " + e.staticAttr);
		System.out.println("Class StaticAttr: " + Employee.staticAttr);
		System.out.println("identity: " + e.identity);
	}
}
