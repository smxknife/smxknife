package com.smxknife.springboot.v2.ex03.test;

public class Demo03 {
	public static void main(String[] args) {

		assignOut(Animal.class, Animal.class);
		assignOut(Animal.class, Bird.class);
		assignOut(Bird.class, Animal.class);
		assignOut(Animal.class, Dove.class);
		assignOut(Penguin.class, Dove.class);
		assignOut(Fly.class, Bird.class);
		assignOut(Fly.class, Dove.class);


	}

	static void assignOut(Class cls1, Class cls2) {
		System.out.println(cls1.getSimpleName() + " isAssignableFrom " + cls2.getSimpleName() + " : " + cls1.isAssignableFrom(cls2));
	}
}
