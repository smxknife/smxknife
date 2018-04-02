package com.smxknife.java.ex4;

public class InnerClassMain {
	public static void main(String[] args) {
		Animal animal = new Animal();
		System.out.println(animal.getType());

		Animal.Bird bird = animal.new Bird();
		System.out.println(bird.getType());

		Animal.Fish fish = new Animal.Fish();
	}
}
