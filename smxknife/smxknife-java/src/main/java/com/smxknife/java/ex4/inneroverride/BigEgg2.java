package com.smxknife.java.ex4.inneroverride;

public class BigEgg2 extends Egg2 {

	public class Yolk extends Egg2.Yolk {
		public Yolk(){
			System.out.println("BigEgg2.Yolk");
		}
		public void f() {
			System.out.println("BigEgg2.Yolk.f");
		}
	}
	public BigEgg2() {
		System.out.println("New BigEgg2");
		insertYolk(new Yolk());
	}

	public static void main(String[] args) {
		Egg2 egg2 = new BigEgg2();
		egg2.g();
	}
}
