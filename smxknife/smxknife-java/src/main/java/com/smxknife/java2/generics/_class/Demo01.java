package com.smxknife.java2.generics._class;

/**
 * @author smxknife
 * 2019-04-17
 */
public class Demo01 {
	public static void main(String[] args) {
		Group<String> stringGroup = new Group<>();
		stringGroup.getObj();

		Group<Integer> integerGroup = new Group<>();
		integerGroup.getObj();

//		System.out.println(integerGroup instanceof Group<Integer>);
		System.out.println(integerGroup instanceof Group<?>);


	}
}
