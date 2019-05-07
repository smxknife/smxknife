package com.smxknife.java.ex26;

/**
 * @author smxknife
 * 2019-05-07
 */
public class StringObjAndIntObj {
	public static void main(String[] args) {

		System.out.println("begin");

		IntObj intObj = new IntObj(1);
		StringObj stringObj = new StringObj("1");

		System.out.println("filled");
		try {
			Thread.currentThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("finished");
	}
}
