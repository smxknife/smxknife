package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/2/1
 */
public class HashCodeDemo {
	public static void main(String[] args) {
		String a = new String("aaa");
		String b = new String("aaa");
		System.out.println(a == b);
		System.out.println(a.hashCode());
		System.out.println(b.hashCode());

		System.out.println(System.identityHashCode(a));
		System.out.println(System.identityHashCode(b));

		System.out.println("--------------------------------");

		Object obj1 = new Object();
		Object obj2 = new Object();

		System.out.println(obj1.hashCode());
		System.out.println(obj2.hashCode());
		System.out.println(System.identityHashCode(obj1));
		System.out.println(System.identityHashCode(obj2));

	}
}
