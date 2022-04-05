package com.smxknife.java2.reflection.demo01;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author smxknife
 * 2019-04-19
 */
public class ClassDemo extends Object implements Serializable {

	private String name;
	public String firstName;
	String lastName;
	protected String fullName;

	public void setName() {}
	void setFirstName() {}
	protected void setLastName() {}


	public static void main(String[] args) {
		System.out.println(ClassDemo.class.toString()); // class com.smxknife.java2.reflection.demo01.ClassDemo
		System.out.println(ClassDemo.class.toGenericString()); // public class com.smxknife.java2.reflection.demo01.ClassDemo
		System.out.println(ClassDemo.class.isPrimitive());
		System.out.println(ClassDemo.class.isAnnotation());
		System.out.println(ClassDemo.class.getDeclaringClass());
		System.out.println(ClassDemo.class.getComponentType());
		System.out.println(ClassDemo.class.getSuperclass());

		System.out.println("Field ================================");

		Arrays.asList(ClassDemo.class.getFields()).forEach(field -> {
			System.out.println(field.getType().getCanonicalName());
			System.out.println(field.getName());
			System.out.println(field.toGenericString());
			System.out.println("-----------");
		});

		System.out.println("Declared Field ================================");

		Arrays.asList(ClassDemo.class.getDeclaredFields()).forEach(field -> {
			System.out.println(field.getType().getCanonicalName());
			System.out.println(field.getName());
			System.out.println(field.toGenericString());
			System.out.println(field.getClass());
			System.out.println("-----------");
		});

		System.out.println("Method ================================");

		Arrays.asList(ClassDemo.class.getMethods()).forEach(method -> {
			System.out.println(method.getName());
			System.out.println(method.getClass());
			System.out.println(method.getDeclaringClass().getCanonicalName());
			System.out.println(method.toGenericString());
			System.out.println("-----------");
		});
	}
}
