package com.smxknife.java2.thread.designpattern._07ThreadPerMessage;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {
	public static void main(String[] args) {
		// Thread-Per-Message模式：这项工作交给你了

		/** 简介
		 * 1. 每个消息一个线程，每个处理都是一个单独的线程
		 * 2. 可以简单理解为早期的io编程模型，每接入一个socket都创建一个线程进行处理
		 */

		/** 角色
		 * 1. Client(委托人)：只管发出请求，不管如何实现
		 * 2. Host：Host收到client的请求后，会创建并启动一个线程，新的线程使用Helper角色来处理请求
		 * 3. Helper
		 */

		/**
		 * 1. 提高响应性，缩短延迟时间
		 * 2. 适用于操作顺序没有要求时
		 * 3. 适用于不需要返回值时
		 * 4. 应用于服务器
		 */

		/** 相关的设计模式
		 * 1. Future模式：
		 * 2. Worker Thread模式：该模式会提前启动好线程，循环使用，提高程序性能
		 */

		/** 线程和进程的区别
		 * 1. 每个进程都有独立的内存空间，一个进程不可擅自读取，写入其他进程的内存
		 * 2. 线程之间共享内存
		 */

		/** 线程的上下文切换
		 * 1. 进程和线程的另一个区别就是上下文切换的频度
		 * 2. 进程上下文切换比较耗时
		 * 3. 线程相对较快
		 */
	}
}
