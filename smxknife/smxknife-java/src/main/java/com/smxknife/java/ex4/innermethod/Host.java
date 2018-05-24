package com.smxknife.java.ex4.innermethod;

public class Host {
	static int hi =  1;
	int y = 0;

	public People getFriend() {

		int i = 9;
		class Friend implements People {
			private String name;
			private Friend(String name) {
				int k = i;
				int h = hi;
				int yy = y;

				this.name = name;
			}
			public String getPeopleName() {
				return this.name;
			}
		}
		return new Friend("hello");
	}


}
