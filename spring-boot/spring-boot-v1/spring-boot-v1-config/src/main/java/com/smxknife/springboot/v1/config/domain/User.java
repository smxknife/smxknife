package com.smxknife.springboot.v1.config.domain;

import javafx.scene.paint.Color;

public class User {
	private Color color;

	public User(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
}
