package com.smxknife.java2.generics._interface;

/**
 * @author smxknife
 * 2019-04-17
 */
//public class GroupOptT implements GroupOpt<T> { // 编译报错
public class GroupOptT<T> implements GroupOpt<T> {
	@Override
	public T getObj() {
		return null;
	}
}
