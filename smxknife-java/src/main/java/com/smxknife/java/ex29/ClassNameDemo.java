package com.smxknife.java.ex29;

/**
 * @author smxknife
 * 2020/6/1
 */
public class ClassNameDemo {
	public static void main(String[] args) {
		System.out.println(Animal.class.getCanonicalName());
		System.out.println(Animal.class.getSimpleName());
		System.out.println(Animal.class.getName());
	}
}
