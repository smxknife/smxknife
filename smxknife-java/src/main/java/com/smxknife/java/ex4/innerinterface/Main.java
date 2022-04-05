package com.smxknife.java.ex4.innerinterface;

public class Main {
	public static void main(String[] args) {
		Move move = new AnimalMoveImpl();
		move.run();
		move.walk();
	}
}
