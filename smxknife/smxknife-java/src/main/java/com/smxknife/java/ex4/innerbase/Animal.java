package com.smxknife.java.ex4.innerbase;

public class Animal {

	private int type = 0;
	private String name = "default";
	private static String test;

	public int getType() {
		return type;
	}

	class Bird {
		final static int i = 1;
		private int type = 1;

		public int getType() {
			return type;
		}

		public String getName() {
			String name1 = name;
			String name2 = Animal.this.name;
			return name;
		}
	}

	static class Fish {
		private int type = 2;

		public int getType() {
			test = "";
			return type;
		}
	}
}
