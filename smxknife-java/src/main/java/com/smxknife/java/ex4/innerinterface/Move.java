package com.smxknife.java.ex4.innerinterface;

public interface Move {

	void run();
	void walk();

	class Run {

		public void move() {
			System.out.println("run");
		}
	}

	class Walk {
		public void move() {
			System.out.println("walk");
		}
	}
}
