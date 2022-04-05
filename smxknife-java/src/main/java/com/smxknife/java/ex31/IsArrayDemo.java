package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/7/13
 */
public class IsArrayDemo {
	public static void main(String[] args) {

		Object[] objArray = new Object[]{};
		int[] intArray = new int[] {};
		System.out.println(objArray.getClass());
		System.out.println(objArray.getClass().isArray());
		System.out.println(intArray.getClass());
		System.out.println(intArray.getClass().isArray());
	}
}
