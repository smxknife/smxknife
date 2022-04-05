package com.smxknife.java.ex20;

/**
 * @author smxknife
 * 2018-12-02
 */
public enum EnumDemo {

	Q, P, E;

	public String index;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public static void main(String[] args) {
		EnumDemo e = EnumDemo.E;
		e.setIndex("E_index");
		System.out.println(e.getIndex());

		EnumDemo q = EnumDemo.Q;
		System.out.println(q.getIndex());

		EnumDemo d = EnumDemo.E;
		System.out.println(d.getIndex());
	}
}
