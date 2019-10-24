package com.smxknife.java2.thread.designpattern._12ActiveObject;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {
	public static void main(String[] args) {
		// Active Object模式：接收异步消息的主动对象

		/** 简介
		 * 1. Active主动的意思，Active Object就是"主动对象"的意思，一般指"自己特有的线程"。因此举例来说java.lang.Thread实例就是一种主动对象
		 * 2. 不过在Active Object模式中出场的主动对象，
		 *      - 不仅仅是"有自己特有的线程"。
		 *      - 同时还具有可以从外部接收
		 *      - 和处理异步消息
		 *      - 并根据需要返回处理结果的特征
		 */

		/** 相关角色
		 * 1. Client：调用ActiveObject角色方法来委托处理，能够调用的只有ActiveObject提供的方法
		 * 2. ActiveObject：定义了主动对象向client提供的接口
		 * 3. Proxy：负责将方法调用转换为MethodRequest角色的对象。转换后的MethodRequest角色会被传递给Schedule角色。
		 *      - Proxy角色
		 */
	}
}
