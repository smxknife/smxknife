package com.smxknife.java2.thread.designpattern._04Balking;

/**
 * @author smxknife
 * 2019/9/25
 */
public class _Run {
	public static void main(String[] args) {
		// Balking模式：不需要就算了

		/** 简介
		 * 1. 停止并返回的意思
		 * 2. 与Guarded Suspension模式一样，都存在守护条件。但是在本模式中，如果守护条件不成立，则立即中断处理
		 */

		/** 使用场景
		 * 1. 并不需要执行时
		 * 2. 不需要等待守护条件成立时
		 * 3. 守护条件仅在第一次成立时
		 */

		/** 返回结果
		 * 1. 忽略balk：不用通知调用端发生balk
		 * 2. 通过返回值表示：如通过true和false来表示
		 * 3. 通过异常来表示
		 */

		/** 相关的设计模式
		 * 1. Guarded Suspension模式
		 * 2. Observer模式：多线程环境中使用Observer模式时，有时需要使用Balking模式，
		 *      当Subject角色通知Observer角色状态发生变化时，如果Observer角色状态不适合处理该通知，则balk该通知
		 */
	}
}
