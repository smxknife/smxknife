package com.smxknife.java2.jvm.refference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * @author smxknife
 * 2019/9/5
 */
public class SoftReferenceDemo {
	public static void main(String[] args) {
		// SoftReference垃圾回收时不会被处理，但是当即将要出现OOM时，会处理的
		SoftReference<String> hello = new SoftReference<>("Hello");
		String val = hello.get();
		System.out.println(val);

		ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
		SoftReference<String> world = new SoftReference<>("World", referenceQueue);

		System.out.println(world.get());

		try {

		} catch (OutOfMemoryError e) {

		}
	}
}
