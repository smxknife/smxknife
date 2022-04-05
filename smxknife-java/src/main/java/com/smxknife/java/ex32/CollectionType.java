package com.smxknife.java.ex32;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2020/11/10
 */
public class CollectionType {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		System.out.println(list.getClass().getName());
		System.out.println(List.class.isAssignableFrom(list.getClass()));

		System.out.println(List.class.equals(list.getClass()));

		System.out.println(list.getClass() instanceof Class);
		System.out.println(Class.class.isAssignableFrom(list.getClass()));
	}
}
