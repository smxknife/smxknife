package com.smxknife.java.ex4.inneroverride;

public class BigEgg extends Egg {

	class Yolk {
		public Yolk() {
			System.out.println("BigEgg.Yolk");
		}
	}

	public static void main(String[] args) {
		new BigEgg();
	}
}
