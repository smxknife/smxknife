package com.smxknife.java.ex31;

/**
 * @author smxknife
 * 2020/7/8
 */
public class StaticFieldDemo {
	public static void main(String[] args) {
		A a = new A();
		a.sa(); // 可以访问，编译不报错，但是会增加编译器的编译成本，应该直接写A.sa();
		A.sa();
		a.sf();
	}


}

class A {

	static void sa() {}

	void sf() {}
}
