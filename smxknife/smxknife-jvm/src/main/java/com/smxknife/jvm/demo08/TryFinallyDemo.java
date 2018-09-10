package com.smxknife.jvm.demo08;

public class TryFinallyDemo {
	public static void main(String[] args) {
		try {
			System.out.println("TryFinallyDemo.main.try");
		} finally {
			System.out.printf("TryFinallyDemo.main.finally");
		}
	}
}
