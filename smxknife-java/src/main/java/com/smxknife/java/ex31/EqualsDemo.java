package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/7/27
 */
public class EqualsDemo {
	public static void main(String[] args) {
		Obj obj1 = new Obj();
		Obj obj2 = new Obj();
		System.out.println(obj1 == obj2);

	}

	static class Obj {
		int age;

		@Override
		public boolean equals(Object obj) {
			return this.age == ((Obj)obj).age;
		}
	}
}
