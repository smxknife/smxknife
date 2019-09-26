package com.smxknife.java2.thread.waitnotify;

/**
 * @author smxknife
 * 2019/9/24
 */
public class _Run_wait_notify_with_inner_object_lock {

	public static void main(String[] args) {
		Obj obj = new Obj();

		new Thread(() -> obj.doWaitWithInnerObjectLock(), "th-1").start();
		new Thread(() -> obj.doNotifyWithInnerObjectLock(), "th-2").start();

		System.out.println("这两个方法有个重点就是，采用是是lock.wait()还是this.wait()，notify方法也一样，\r\n" +
				"如果使用的是this.wait()，那么同样会抛出异常，但是如果调用lock.wait()，那么就会正常执行，\r\n" +
				"说明，wait和notify必须在加锁的对象上调用");
	}
}
