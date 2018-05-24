package com.smxknife.java.ex4.inneroverride;

public class Egg {

	private Yolk y;

	protected class Yolk {
		public Yolk() {
			System.out.println("Egg.Yolk");
		}
	}

	public Egg() {
		System.out.println("new egg()");
		y = new Yolk();
	}
}
