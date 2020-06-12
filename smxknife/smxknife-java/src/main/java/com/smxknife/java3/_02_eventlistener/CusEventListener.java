package com.smxknife.java3._02_eventlistener;

import java.util.EventListener;

/**
 * @author smxknife
 * 2019/12/29
 */
public class CusEventListener implements EventListener {
	/**
	 * 事件发生后的回调方法
	 */
	public void fireCusEvent(CusEvent event) {
		EventSourceObject source = (EventSourceObject) event.getSource();
		System.out.println("My name has been changed!");
		System.out.println("I got a new name,named \"" + source.getName() + "\"");
	}
}
