package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2019/11/18
 */
public class InnerThisDemo {

	public static void main(String[] args) {
		InnerThisDemo demo = new InnerThisDemo();
		demo.new Inner().output();
	}

	public class Inner {

		public void output() {
			System.out.println(this.getClass());
		}
	}
}
