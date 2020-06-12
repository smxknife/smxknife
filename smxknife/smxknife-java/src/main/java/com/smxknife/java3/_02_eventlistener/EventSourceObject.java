package com.smxknife.java3._02_eventlistener;

import lombok.Getter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author smxknife
 * 2019/12/29
 */
public class EventSourceObject {
	@Getter
	private String name;
	/**
	 * 监听器容器
	 */
	private Set<CusEventListener> listeners;

	public EventSourceObject() {
		this.listeners = new HashSet<>();
		this.name = "defaultName";
	}

	/**
	 * 给事件源注册监听器
 	 */
	public void addCusListener(CusEventListener cel) {
		this.listeners.add(cel);
	}

	/**
	 * 当事件发生时，通知注册在该事件源上的所有监听器做出相应的反应（调用回调方法）
	 */
	protected void notifies() {
		CusEventListener cel = null;
		Iterator<CusEventListener> iterator = this.listeners.iterator();
		while (iterator.hasNext()) {
			cel = iterator.next();
			cel.fireCusEvent(new CusEvent(this));
		}
	}

	public void setName(String name) {
		if (!this.name.equals(name)) {
			this.name = name;
			notifies();
		}
	}
}
