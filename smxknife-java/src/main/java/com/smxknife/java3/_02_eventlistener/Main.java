package com.smxknife.java3._02_eventlistener;

/**
 * @author smxknife
 * 2019/12/29
 */
public class Main {
	public static void main(String[] args) {
		EventSourceObject object = new EventSourceObject();
		object.addCusListener(new CusEventListener());
		object.setName("Test");
	}
}
