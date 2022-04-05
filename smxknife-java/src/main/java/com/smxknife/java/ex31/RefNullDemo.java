package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/3/9
 */
public class RefNullDemo {
	public static void main(String[] args) {
		RefNullDemo demo = new RefNullDemo();
		Obj obj = new Obj();
		RefA refA = new RefA();
		refA.obj = obj;

		RefB refB = new RefB();
		refB.obj = obj;

		System.out.println(refA.obj);
		System.out.println(refB.obj);

		refA.obj = null;
		System.out.println(refA.obj);
		System.out.println(refB.obj);
	}
}

class Obj {}

class RefA {
	Obj obj;
}

class RefB {
	Obj obj;
}
