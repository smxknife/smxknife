package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2020/1/17
 */
public class InstanceOfDemo {
	public static void main(String[] args) {
		CC cc = new CC();
		System.out.println(cc instanceof IA);
		System.out.println(cc instanceof IB);
		System.out.println(cc instanceof IC);
		System.out.println(cc instanceof ID);
	}
}

interface IA {}

interface IB extends IA {}

interface IC {}

interface ID extends IB, IC {}

class CC implements ID {}