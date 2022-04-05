package com.smxknife.java2.thread.designpattern._01SingleThreadedExecution;

/**
 * @author smxknife
 * 2019/9/24
 */
public class _Run {
	// Single Threaded Execution ：能通过的只有一个人
	// Single Threaded Execution模式是单线程执行，是多线程的基础
	// Single Threaded Execution有时也称为临界区
	// 归纳
	/**
	 * 1. SharedResource(共享资源) 被多个线程访问
	 * 2. 存在死锁的可能
	 *      2.1 存在多个共享资源
	 *      2.2 并且一个线程需要获取多个资源
	 *      2.3 获取共享资源的顺序是不固定的
	 */
	/** 线程安全的的集合方法java.util.Collections
	 * 		Collections.synchronizedCollection(Collection);
	 * 		Collections.synchronizedList(List);
	 * 	        - 该方法需要注意的是，add等操作是不需要加synchronized，但是遍历是需要加锁的
	 * 	        - synchronized(this) {
	 * 	            for(Obj obj : list) {}
	 * 	        }
	 * 		Collections.synchronizedMap(Map);
	 * 		Collections.synchronizedNavigableMap(NavigableMap);
	 * 		Collections.synchronizedSet(Set);
	 * 		Collections.synchronizedSortedMap(SortedMap);
	 * 		Collections.synchronizedSortedSet(SortedSet);
	 */

	/** 相关的设计模式
	 * Guarded Suspension模式：在检查对象状态部分使用了Single Threaded Execution模式
	 * Read-Write Lock模式：多个线程可以同时执行read方法，write方法就需要等待；检查线程种类和个数也使用了Single Threaded Execution
	 * Immutable模式：
	 * Thread-Specific Storage模式：确保每个线程都有其固有的区域，且这块区域仅有一个线程访问，所以无需保护方法
	 * Before/After模式：try {} finally {}就是典型的before/after模式，try总是在前执行，无论是否正常，finally总是在try之后执行
	 * 信号量Semaphore
	 */

	public static void main(String[] args) {

	}
}
