package com.smxknife.java2.generics;

import com.smxknife.java2.generics._class.Group;

/**
 * @author smxknife
 * 2019-04-17
 */
public class ArrayGeneric {
	public static void main(String[] args) {
//		Group<String>[] groups = new Group<String>[10];// 编译报错，不能创建确切类型的范型数组，因为java的泛型运行期是类型擦除，都是Object类型
		Group<String> [] groups = new Group[10];
		Group<?>[] groups1 = new Group<?>[10];
		Group<Integer>[] groupIntegers = (Group<Integer>[]) new Group[10];// 这是可以的
//		Group<Integer>[] integerGroups = new Group<?>[10]; // 编译报错，前后类型不匹配
		Group<Integer>[] groupIntegers2 = (Group<Integer>[]) new Group<?>[10];// 这样是可以的，但是结果不安全

		// -------------------------  第二轮
		Group<String> mygroups = new Group<String>();



	}
}
