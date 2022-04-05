package com.smxknife.java2.thread.waitnotify;

/**
 * @author smxknife
 * 2019/9/24
 */
public class _Run_wait_notify_nolock {

	public static void main(String[] args) {
		Obj obj = new Obj();

//		new Thread(() -> obj.doWaitNoLock(), "th-1").start();
		new Thread(() -> obj.doNotifyNoLock(), "th-2").start();

		System.out.println("该示例证明了，无论是wait还是notify方法，都需要加锁，否则会抛出java.lang.IllegalMonitorStateException异常");

	}
}
