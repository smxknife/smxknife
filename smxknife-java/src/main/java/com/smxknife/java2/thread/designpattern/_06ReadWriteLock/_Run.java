package com.smxknife.java2.thread.designpattern._06ReadWriteLock;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {
	public static void main(String[] args) {
		// Read-Write Lock模式：大家一起读没有问题，但是读的时候不能写，写的时候不能读

		/** Read-Write Lock中的角色
		 * 1. Reader：对SharedResource执行read操作
		 * 2. Writer：对SharedResource执行write操作
		 * 3. SharedResource：共享资源
		 * 4. ReadWriteLock：读写锁
		 */

		/** 相关的设计模式
		 * 1. Immutable模式
		 * 2. Single Threaded Execution模式
		 * 3. Guarded Suspension模式
		 * 4. Before/After模式
		 * 5. Strategized Locking模式：
		 */
	}
}
