package com.smxknife.jvm.demo09;

public class MonitorDemo {
	public static void main(String[] args) {
		MonitorDemo monitor = new MonitorDemo();
		monitor.test("hello");
	}

	public void test(String param) {
		synchronized (param) {
			System.out.println(param);
		}
	}
}
