package com.smxknife.jvm.demo05;

public class UseObjectDemo {
	public static void main(String[] args) {
		UseObjectDemo demo = new UseObjectDemo();

		MyObj obj = new MyObj();
		demo.demoMethod(obj);
	}

	MyObj demoMethod(MyObj obj) {
		return obj;
	}
}
