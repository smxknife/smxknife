package com.smxknife.java.ex4.inneroverride;

public class Egg2 {

	protected class Yolk {
		public Yolk() {
			System.out.println("Egg2.Yolk");
		}

		void f() {
			System.out.println("Egg2.Yolk.f");
		}
	}

	public Egg2() {
		System.out.println("New Egg2");
	}

	private Yolk y = new Yolk();

	public void insertYolk(Yolk y) {
		this.y = y;
	}
	public void g() {
		y.f();
	}
}
