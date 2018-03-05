package com.smxknife.java.ex2;

public class RoundGlyph extends Glyph {

	private int radius = 1;
	public RoundGlyph(int radius) {
		this.radius = radius;
		System.out.println("round glyph radius = " + radius);
	}

	@Override
	void draw() {
		System.out.println("round glyph draw radius = " + radius);
	}


}
