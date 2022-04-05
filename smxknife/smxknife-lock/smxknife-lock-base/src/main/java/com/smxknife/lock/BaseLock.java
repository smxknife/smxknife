package com.smxknife.lock;

/**
 * 分布式锁的目的：
 * - 为了解决分布式系统中多个进程之间的相互干扰，一种协调机制，解决分布式资源共享问题
 * 分布式锁解决的问题
 * - 1. 多线程竞争
 * - 2. 多进程竞争
 * - 3. 是否可重入
 * - 4. 具备锁失效机制，防止死锁
 * - 5. 高可用
 * - 6. 高性能
 * - 7. 具备非阻塞特性
 * - 8. 可续租
 * @author smxknife
 * 2021/6/11
 */
public abstract class BaseLock {

	// lock

	// unlock

}
