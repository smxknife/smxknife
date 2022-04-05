package com.smxknife.java2.thread.designpattern._11ThreadSpecificStorage;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {
	public static void main(String[] args) {
		// Thread-Specific Storage模式：一个线程一个储物柜

		/** 简介
		 * 1. ThreadLocal就相当于储物间，里面会有很多储物柜
		 */

		/** 角色
		 * 1. Client(委托者)
		 * 2. TSObjectProxy(线程特有对象代理人)
		 * 3. TSObjectCollection（线程特有对象的集合）
		 * 4. TSObject（线程特有对象）
		 */

		/** 相关设计模式
		 * 1. Singleton模式
		 * 2. Worker Thread模式
		 * 3. Single Threaded Execution模式
		 * 4. Proxy模式
		 */
	}
}
