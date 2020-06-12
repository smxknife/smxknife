package com.smxknife.patterns.singleton.demo02;

import java.util.Objects;

/**
 * @author smxknife
 * 2019-06-18
 */
public class Mode_LazyInitializationCanPreventReflect2 {

	static {
		System.out.println("构造初始化");
		if (Objects.nonNull(LazyInitializationHolder.INSTANCE)) {
			throw new RuntimeException("不可实例化多个单例");
		}
	}

	private Mode_LazyInitializationCanPreventReflect2() {
		if (Objects.nonNull(LazyInitializationHolder.INSTANCE)) {
			throw new RuntimeException("不可实例化多个单例");
		}
	}

	private static class LazyInitializationHolder {
		private static final Mode_LazyInitializationCanPreventReflect2 INSTANCE = new Mode_LazyInitializationCanPreventReflect2();
	}

	public static Mode_LazyInitializationCanPreventReflect2 getInstance() {
		/*
		 * 1）当首次调用getInstance方法时，jvm将加载这个静态类
		 * 2）Java语言规范确保类初始化阶段是按顺序的。所有后续的并发执行都将返回相同且被正确被初始化的实例，无需任何同步
		 */
		return LazyInitializationHolder.INSTANCE;
	}
}
