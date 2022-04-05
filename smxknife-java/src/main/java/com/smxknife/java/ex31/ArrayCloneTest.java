package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/3/5
 */
public class ArrayCloneTest {
	public static void main(String[] args) {
		Object[] ori = new Object[2];
		ori[0] = new Object();
		ori[1] = new Object();
		System.out.println(ori);
		for (Object o : ori) {
			System.out.println(o);
		}

		Object[] clone = ori.clone();
		System.out.println(clone);
		for (Object o : clone) {
			System.out.println(o);
		}

		System.out.println("------------");

		Obj[] objs = new Obj[2];
		objs[0] = new Obj();
		objs[1] = new Obj();

		for (Obj obj : objs) {
			System.out.println(obj);
		}

		Obj[] clone1 = objs.clone();
		for (Obj obj : clone1) {
			System.out.println(obj);
		}
	}

	static class Obj implements Cloneable {
		@Override
		protected Object clone() throws CloneNotSupportedException {
			return super.clone();
		}
	}
}
