package com.smxknife.java2.generics._class;

import lombok.Getter;
import lombok.Setter;

/**
 * 范型类
 * @author smxknife
 * 2019-04-17
 */
public class Group<T> {

	@Getter
	@Setter
	private T obj;
}
