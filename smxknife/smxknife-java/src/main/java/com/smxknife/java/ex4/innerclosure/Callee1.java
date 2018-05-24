package com.smxknife.java.ex4.innerclosure;

public class Callee1 implements Incrementable {
	private int i = 0;
	public void increment() {
		i++;
		System.out.println(i);
	}
}
