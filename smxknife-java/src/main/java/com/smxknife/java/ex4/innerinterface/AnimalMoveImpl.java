package com.smxknife.java.ex4.innerinterface;

public class AnimalMoveImpl implements Move {
	@Override
	public void run() {
		new Run().move();
	}

	@Override
	public void walk() {
		new Walk().move();
	}
}
