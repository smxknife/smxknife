package com.smxknife.java.ex2;

public class Glyph {

	void draw() {
		System.out.println("glyph draw");
	}

	public Glyph() {
		System.out.println("glyph before draw");
		draw();
		System.out.println("glyph after draw");
	}
}
