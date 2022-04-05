package com.smxknife.java2.thread.designpattern._10TwoPhaseTermination;

/**
 * @author smxknife
 * 2019/9/26
 */
public class _Run {
	public static void main(String[] args) {
		// Tow-Phase Termination模式：先收拾房间再睡觉，分两段终止

		/** 角色
		 * 1. TerminationRequester(终止请求发出者)
		 * 2. Terminator（终结者）
		 */

		/** 相关设计模式
		 * 1. Before/After模式： try finally相当于Before/After模式
		 * 2. Multiphase Cancellation模式：如果一段时间线程没有终止，那么程序会逐渐发出更强的终止请求
		 * 3. Multi-Phase Startup模式：
		 * 4. Balking模式
		 */
	}
}
