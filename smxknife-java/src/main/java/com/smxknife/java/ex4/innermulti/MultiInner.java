package com.smxknife.java.ex4.innermulti;

public class MultiInner {
	private int val = 0;
	class Inner1 {
		{
			System.out.println(val);
		}
		class Inner2 {
			{
				System.out.println(val);
			}
			class Inner3 {
				{
					System.out.println(val);
				}
			}
		}
	}
}
