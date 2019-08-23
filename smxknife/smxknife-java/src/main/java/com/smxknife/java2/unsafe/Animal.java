package com.smxknife.java2.unsafe;

import lombok.*;

/**
 * @author smxknife
 * 2019-08-20
 */
@Getter@Setter
//@ToString
public class Animal {

	private String name;
	private int legs;

	public Animal() {
		System.out.println("Animal constructor");
	}

	public Animal(String name, int legs) {
		this.name = name;
		this.legs = legs;
		System.out.println("Animal constructor with param");
	}

	public String string() {
		return "Animal{" +
				"name='" + name + '\'' +
				", legs=" + legs +
				'}';
	}

}
