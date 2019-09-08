package com.smxknife.java2.jvm.refference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * @author smxknife
 * 2019/9/6
 */
public class PhantomReferenceDemo {
	public static void main(String[] args) {

		ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
		PhantomReference<String> reference = new PhantomReference<>("test", referenceQueue);

		System.out.println(reference.get());
		System.out.println(referenceQueue.poll().get());
	}
}
