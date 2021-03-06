package com.smxknife.java2.serializable;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * @author smxknife
 * 2018/11/2
 */
public class EmployeeSerializableDemo {
	public static void main(String[] args) {
		Employee e = new Employee();
		e.name = "test";
		e.address = "hangzhou";
		e.SSN = 12345678;
		e.number = 110;
		e.staticAttr = "static-attr";
		Employee.staticAttr = "class-static";
		e.identity = 1000;

		try {
			FileOutputStream fos = new FileOutputStream("./employee.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(e);
			oos.close();
			fos.close();
			System.out.println("saved");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
