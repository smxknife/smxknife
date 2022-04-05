package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/3/15
 */
public class RefNullTest {
	public static void main(String[] args) {
		Object object = new Object();
		System.out.println(object);

		RefObj refObj = new RefObj();
		refObj.ref = object;
		object = null;
		System.out.println(object);
		System.out.println(refObj.ref);
	}
}

class RefObj {
	Object ref;
}
