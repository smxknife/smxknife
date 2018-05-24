package com.smxknife.java.ex4.innerclosure;

public class Caller {
	private Incrementable callbackRef;
	Caller(Incrementable cbr) {
		this.callbackRef = cbr;
	}
	void go() {
		callbackRef.increment();
	}
}
