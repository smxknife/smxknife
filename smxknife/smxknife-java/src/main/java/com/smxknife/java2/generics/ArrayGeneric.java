package com.smxknife.java2.generics;

import com.smxknife.java2.generics._class.Group;

/**
 * @author smxknife
 * 2019-04-17
 */
public class ArrayGeneric {
	public static void main(String[] args) {
//		Group<String>[] groups = new Group<String>[10];// 编译报错，不能创建确切类型的范型数组
		Group<String> [] groups = new Group[10];
		Group<?>[] groups1 = new Group<?>[10];
		Group<Integer>[] groupIntegers = (Group<Integer>[]) new Group[10];// 这是可以的
//		Group<Integer>[] integerGroups = new Group<?>[10]; // 编译报错
		Group<Integer>[] groupIntegers2 = (Group<Integer>[]) new Group<?>[10];// 这样是可以的，但是结果不安全


	}
}
