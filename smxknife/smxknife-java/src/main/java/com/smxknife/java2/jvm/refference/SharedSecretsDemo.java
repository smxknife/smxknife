package com.smxknife.java2.jvm.refference;

import sun.misc.JavaLangAccess;
import sun.misc.JavaLangRefAccess;
import sun.misc.SharedSecrets;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author smxknife
 * 2019/9/6
 */
public class SharedSecretsDemo {

	static List<Object> objects = new ArrayList<>();

	public static void main(String[] args) {
		JavaLangAccess javaLangAccess = SharedSecrets.getJavaLangAccess();
		System.out.println(javaLangAccess);

		Throwable throwable = new Throwable();
		int stackTraceDepth = javaLangAccess.getStackTraceDepth(throwable);
		for (int i = 0; i < stackTraceDepth; i++) {
			StackTraceElement stackTraceElement = javaLangAccess.getStackTraceElement(throwable, i);
			System.out.println(stackTraceElement);
		}

		Stream.iterate(0, i -> i + 1).limit(100).forEach(idx -> {
			objects.add(new Byte[1024 * 1024 * 1024]);
		});

		SharedSecrets.setJavaLangRefAccess(new JavaLangRefAccess() {

			/**
			 * Help ReferenceHandler thread process next pending
			 * {@link java.lang.ref.Reference}
			 *
			 * @return {@code true} if there was a pending reference and it
			 *         was enqueue-ed or {@code false} if there was no
			 *         pending reference
			 */
			@Override
			public boolean tryHandlePendingReference() {
				System.out.println("eeeee");
				return false;
			}
		});

	}
}
