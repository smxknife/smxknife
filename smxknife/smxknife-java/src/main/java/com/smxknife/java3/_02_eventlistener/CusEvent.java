package com.smxknife.java3._02_eventlistener;

import lombok.Getter;
import lombok.Setter;

import java.util.EventObject;

/**
 * @author smxknife
 * 2019/12/29
 */
public class CusEvent extends EventObject {
	private static final long serialVersionUID = 1L;
	// 事件源
	@Getter@Setter
	private Object source;
	/**
	 * Constructs a prototypical Event.
	 *
	 * @param source The object on which the Event initially occurred.
	 * @throws IllegalArgumentException if source is null.
	 */
	public CusEvent(Object source) {
		super(source);
		this.source = source;
	}
}
