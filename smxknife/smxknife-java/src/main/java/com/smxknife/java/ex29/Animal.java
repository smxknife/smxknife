package com.smxknife.java.ex29;

import lombok.ToString;

/**
 * @author smxknife
 * 2019/9/8
 */
@ToString
public class Animal {
	private String name;
	private int age;
	private int legs;

	private Animal(Builder builder) {
		this.name = builder.name;
		this.age = builder.age;
		this.legs = builder.legs;
	}

	public static class Builder {

		private String name;
		private int age;
		private int legs;

		public Builder(String name) {
			this.name = name;
		}

		public Builder age(int age) {
			this.age = age;
			return this;
		}

		public Builder legs(int legs) {
			this.legs = legs;
			return this;
		}

		public Animal build() {
			return new Animal(this);
		}
	}

}
