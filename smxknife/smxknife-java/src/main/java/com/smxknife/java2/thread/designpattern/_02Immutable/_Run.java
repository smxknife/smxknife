package com.smxknife.java2.thread.designpattern._02Immutable;

/**
 * @author smxknife
 * 2019/9/24
 */
public class _Run {
	// Immutable模式 : 想破坏也破坏不了
	// String类就是一个典型的Immutable类，不可修改
	/**
	 * 1. 字段属性初始化不可修改，可以用final进行修饰，但是不是必须的
	 * 2. 对象必须是final
	 */

	/** 何时使用Immutable模式
	 * 1. 实例创建后，状态不再发生变化
	 * 2. 实例是共享的，且被频繁访问时
	 */

	/** JDK中哪些类使用了Immutable模式
	 * 1. java.lang.String
	 * 2. java.math.BigInteger和java.math.BigDecimal
	 * 3. java.util.regex.Pattern
	 * 4. java.lang.Integer等基本类型封装类
	 * 5. java.lang.Void
	 */

	/** 相关的设计模式
	 * 1. Single Threaded Execution模式
	 * 2. Read-Write Lock模式
	 * 3. Flyweight模式：在该模式中，为了提高内存的使用率，也会共享实例，因此Immutable和Flyweight可以同时使用
	 */
}
