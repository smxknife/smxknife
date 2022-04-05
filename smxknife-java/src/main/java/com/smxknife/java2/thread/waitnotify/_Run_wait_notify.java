package com.smxknife.java2.thread.waitnotify;

/**
 * @author smxknife
 * 2019/9/24
 */
public class _Run_wait_notify {

	public static void main(String[] args) {
		Obj obj = new Obj();

		new Thread(() -> obj.doWait(), "th-1").start();
		new Thread(() -> obj.doNotify(), "th-2").start();

		System.out.println("从输出日志来看，th-1先执行获得对象锁，执行doWait方法，这样th-2就排队等待对象锁而无法进入doNotify方法，\r\n" +
				"th-1在sleep的2秒内，th-2依然无法获得对象锁，但是当th-1执行wait()之后，th-2可以进入doNotify方法，th-1也没有退出doWait方法，\r\n" +
				"说明th-1暂时让出了对象锁，th-2可以继续执行了。当th-2调用notify方法之后，th-1可以执行了，但是由于对象锁被th-2持有\r\n，" +
				"th-1就必须等待th-2执行完毕后才能继续执行");
	}
}
