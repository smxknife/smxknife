package com.smxknife.java.ex4.innerclosure;

public class Callee2 extends MyIncrement {
	private int i = 0;

	@Override
	public void increment() {
		super.increment();
		i++;
		System.out.println(i);
	}

	// 这里接口和类里面同时存在increment方法，所以如果要重写increment方法是不现实的，这里就只能用到内部类来实现
	private class Closure implements Incrementable {
		public void increment() {
			Callee2.this.increment();
		}
	}

	Incrementable getCallbackRef() {
		return new Closure();
	}
}
