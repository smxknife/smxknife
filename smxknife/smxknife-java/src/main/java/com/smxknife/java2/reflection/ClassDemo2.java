package com.smxknife.java2.reflection;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2019-07-11
 */
public class ClassDemo2 {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
//		Cat cat = new Cat();
//		System.out.println(Animal.class.isAssignableFrom(cat.getClass()));
//		System.out.println(cat.getClass().getSuperclass());
//		System.out.println(cat.getClass().isMemberClass());
//		System.out.println(Animal.class.isAssignableFrom(new Animal().getClass()));
//		System.out.println(cat instanceof Animal);
//		System.out.println(new Animal() instanceof Animal);

		Class<?> aClass = Class.forName(Cat.class.getCanonicalName());
		Object o = aClass.newInstance();
		Method test = aClass.getMethod("test");
		Object invoke = test.invoke(o);

	}
}

class Animal {

	public final void test() {
		System.out.println("animal test");
	}

}

class Cat extends Animal {

}
