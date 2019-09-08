package com.smxknife.java.ex5.extendstest;

public class Main {
	public static void main(String[] args) {
		new Dog();
		System.out.println("1. ++++++++");
		new Dog();
		System.out.println("2. ++++++++");
		new Dog();
		new Dog();

		System.out.println("-----");

		Animal animal = new Animal();
		Dog dog = (Dog) animal;
	}
}
