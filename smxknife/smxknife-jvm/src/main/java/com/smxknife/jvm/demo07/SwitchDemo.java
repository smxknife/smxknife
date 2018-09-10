package com.smxknife.jvm.demo07;

public class SwitchDemo {
	public static void main(String[] args) {
		intSwitch();
		stringSwitch();
	}

	private static void stringSwitch() {
		String cond = "hello";
		String res = "";
		switch (cond) {
			case "hello":
				res = cond + " world";break;
			case "world":
				res = cond + " Hello";break;
				default:break;
		}
	}

	public static void intSwitch() {
		int cond = 3;
		int res = 0;
		switch (cond) {
			case 2:
				res = 0;break;
			case 1:
				res = cond * 10;break;
			case 0:
				res = cond * 20;break;
			default:break;
		}
	}
}
