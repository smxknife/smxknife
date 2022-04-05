package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/3/27
 */
public class ObjectSwap {
	public static void main(String[] args) {
		Obj o1 = new Obj("o1");
		Obj o2 = new Obj("o2");

		swap(o1, o2);
		System.out.println(o1);
		System.out.println(o2);
	}

	private static void swap(Obj o1, Obj o2) {
		Obj temp = o1;
		o1 = o2;
		o2 = temp;
	}

	static class Obj {
		private String name;

		public Obj(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return name;
		}
	}
}
