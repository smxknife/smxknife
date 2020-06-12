package com.smxknife.patterns.singleton.demo02;

import java.io.Serializable;

/**
 * @author smxknife
 * 2019-06-18
 */
public class Mode_LazyInitializationCanPreventSerialize2 implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Mode_LazyInitializationCanPreventSerialize2 INSTANCE = new Mode_LazyInitializationCanPreventSerialize2();

	private Mode_LazyInitializationCanPreventSerialize2() {}

	public static Mode_LazyInitializationCanPreventSerialize2 getInstance() {
		/*
		 * 1）当首次调用getInstance方法时，jvm将加载这个静态类
		 * 2）Java语言规范确保类初始化阶段是按顺序的。所有后续的并发执行都将返回相同且被正确被初始化的实例，无需任何同步
		 */
		return INSTANCE;
	}

	// TODO

	/**
	 * 为什么这个方法可以，需要从源码来看
	 * @return
	 */
	private Object readResolve() {
		return INSTANCE;
	}
}
