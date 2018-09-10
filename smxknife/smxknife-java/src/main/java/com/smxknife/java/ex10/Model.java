package com.smxknife.java.ex10;

public enum Model {

	Hello("nh", "nihao"),
	World("wd", "world");

	private final String key;
	private final String full;

	private Model(String key, String full) {
		this.key = key;
		this.full = full;
	}

	public static Model mod() {
		Model[] values = values();
		for (Model m : values) {
			System.out.println(m);
		}
		return null;
	}
}
