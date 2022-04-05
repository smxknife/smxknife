package com.smxknife.java.ex29;

/**
 * @author smxknife
 * 2019/9/8
 */
public class AnimalDemo {
	public static void main(String[] args) {
		Animal test = new Animal.Builder("test")
				.age(10)
				.build();
		System.out.println(test);
	}
}
