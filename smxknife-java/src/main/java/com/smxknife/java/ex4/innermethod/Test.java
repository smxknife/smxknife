package com.smxknife.java.ex4.innermethod;

public class Test {
	public static void main(String[] args) {
//		Friend friend = new Host().getFriend();
		getFriend();
	}

	public static AbsPeople getFriend() {
		return new AbsPeople() {

			{
				System.out.println("eee");
			}

			public String getPeopleName() {
				return null;
			}
		};
	}
}
