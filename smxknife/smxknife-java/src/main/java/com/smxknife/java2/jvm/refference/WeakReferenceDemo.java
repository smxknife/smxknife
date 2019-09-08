package com.smxknife.java2.jvm.refference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2019/9/5
 */
public class WeakReferenceDemo {
	public static void main(String[] args) throws InterruptedException {
		// 垃圾回收时会回收。不过由于垃圾回收器是一个优先级低的线程，因此不一定会很快发现那些只有若引用的对象
		WeakReference<String> weakReference = new WeakReference<>("Hello");
		System.out.println("WeakReference-1 : " + weakReference.get());

		ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();

		Reference rr = null;
		while ((rr = referenceQueue.poll()) != null) {
			System.out.println("queue: " + rr.get());
		}

		WeakReference<String> weakReference2 = new WeakReference<>("Test", referenceQueue);
		System.out.println("WeakReference-2 : " + weakReference2.get());

		// 由于这里调用System.gc，一直没有执行，所以下面的效果模拟不好
//		System.gc();
//		Runtime.getRuntime().freeMemory();
//		System.gc();


		System.out.println("after gc...");
		TimeUnit.SECONDS.sleep(5);

		System.out.println("Weak...1 : " + weakReference.get());
		System.out.println("Weak...2 : " + weakReference2.get());

		while ((rr = referenceQueue.poll()) != null) {
			System.out.println("queue: " + rr.get());
		}
	}
}
