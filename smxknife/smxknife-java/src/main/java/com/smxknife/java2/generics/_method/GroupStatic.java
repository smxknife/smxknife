package com.smxknife.java2.generics._method;

/**
 * @author smxknife
 * 2019-04-17
 */
public class GroupStatic<T> {

	/**
	 * 会编译报错，
	 * 静态方法不允许使用类上的范型T，如果想用，必须使用范型方法
	 * @param t
	 */
//	public static void test(T t) {
//
//	}

	/**
	 * 将方法声明为范型方法可以使用T，但是此时的T已经跟类上的T没有任何关系了
	 * @param t
	 * @param <T>
	 */
	public static <T> void test(T t) {

	}
}
