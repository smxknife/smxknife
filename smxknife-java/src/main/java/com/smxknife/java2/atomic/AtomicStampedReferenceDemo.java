package com.smxknife.java2.atomic;

import lombok.ToString;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author smxknife
 * 2020/7/27
 */
public class AtomicStampedReferenceDemo {
	public static void main(String[] args) {
		String string = new String("12");
		AtomicStampedReference reference = new AtomicStampedReference(string, 12);

		System.out.println(reference.getReference());

		boolean b = reference.attemptStamp(13, 12);
		System.out.println(reference.getReference() + " | " + b);

		string.replace("2", "3");
		b = reference.attemptStamp(string, 12);
		System.out.println(reference.getReference() + " | " + b);

		System.out.println("=======================");

		Obj obj = new Obj();
		AtomicStampedReference<Obj> objRef = new AtomicStampedReference<>(obj, 1);
		System.out.println(objRef.getReference());

		obj.name = "111";
		boolean b1 = objRef.attemptStamp(obj, 1);
		System.out.println(objRef.getReference() + " | " + b1 + " stamp = " + objRef.getStamp());

		obj.age = 1000;
		b1 = objRef.attemptStamp(obj, 2);
		System.out.println(objRef.getReference() + " | " + b1);

		obj.name = "2222";
		int stamp = objRef.getStamp();
		boolean b2 = objRef.compareAndSet(obj, new Obj(), stamp, stamp + 1);
		System.out.println(objRef.getReference() + " | " + b2 + " | " + objRef.getStamp());

	}

	@ToString
	static class Obj {
		String name;
		int age;
	}
}
