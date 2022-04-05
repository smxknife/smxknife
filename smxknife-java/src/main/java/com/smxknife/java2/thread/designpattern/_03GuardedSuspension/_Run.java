package com.smxknife.java2.thread.designpattern._03GuardedSuspension;

/**
 * @author smxknife
 * 2019/9/24
 */
public class _Run {
	public static void main(String[] args) {
		// Guarded Suspension模式：等我准备好
		/** 简介
		 * 1. Guarded是指被守护、被保卫、被保护的意思
		 * 2. Suspension是暂停的意思
		 * 3. 所以该模式是让线程等待来保证实例的安全性
		 * 4. 示例代码见demo
		 */

		/** 特征: 因为GuardedSuspension可能有很多不同的名称，但是都有相似的特张
		 * 1. 存在循环
		 * 2. 存在条件检查
		 * 3. 因为某种原因而等待：
		 *      因为wait是待在等待队列中停止执行的，所以不会浪费java虚拟机的处理时间；
		 *      但是yield是将优先级让出来，但是线程还在执行，所以会浪费jvm的处理时间；
		 *      还有一点是：wait会把对象锁释放掉，而yield是不会释放锁的，所以不能再synchronized中使用yield
		 */

		/** 相关设计模式
		 * 1. Single Threaded Execution模式
		 * 2. Balking模式：与Guarded Suspension不同的是，Balking模式中，线程不会等待守护条件成立，而是直接返回
		 * 3. Producer-Consumer模式：Producer角色放置数据，Consumer角色获取数据，都会使用Guarded Suspension模式
		 * 4. Future模式：当线程想要获得目标信息，而目标信息还没有准备好，就是用Guarded Suspension模式
		 */
	}
}
