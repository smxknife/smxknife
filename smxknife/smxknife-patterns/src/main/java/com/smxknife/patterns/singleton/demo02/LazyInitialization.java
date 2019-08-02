package com.smxknife.patterns.singleton.demo02;

/**
 * @author smxknife
 * 2019-06-18
 */
public class LazyInitialization {

	private LazyInitialization() {}

	private static class LazyInitializationHolder {
		private static final LazyInitialization INSTANCE = new LazyInitialization();
	}

	public static LazyInitialization getInstance() {
		/*
		 * 1）当首次调用getInstance方法时，jvm将加载这个静态类
		 * 2）Java语言规范确保类初始化阶段是按顺序的。所有后续的并发执行都将返回相同且被正确被初始化的实例，无需任何同步
		 */
		return LazyInitializationHolder.INSTANCE;
	}
}
