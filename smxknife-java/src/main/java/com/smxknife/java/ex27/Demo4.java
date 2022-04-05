package com.smxknife.java.ex27;

/**
 * @author smxknife
 * 2019-05-18
 */
public class Demo4 {
	public static void main(String[] args) {
		DD dd1 = new DD();
		DD dd2 = new DD();
		System.out.println(dd1 == dd2);
		System.out.println(dd1.equals(dd2));

		Eq eq1 = new Eq();
		Eq eq2 = new Eq();
		System.out.println(eq1 == eq2);
		System.out.println(eq1.equals(eq2));
	}
}

class DD {
	int id = 1;
}

class Eq {
	int id = 1;

	@Override
	public boolean equals(Object obj) {
		Eq eq = (Eq) obj;
		return this.id == eq.id;
	}
}
