package com.smxknife.java2.nio.buffer;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * @author smxknife
 * 2020/9/28
 */
public class _15_DirectBufferCleaner {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

		ByteBuffer directByteBuffer = ByteBuffer.allocateDirect(100);

		Class<? extends ByteBuffer> aClass = directByteBuffer.getClass();
		System.out.println("directByteBuffer class = " + aClass.getName());

		Method cleaner = aClass.getMethod("cleaner");
		cleaner.setAccessible(true);
		Object returenValue = cleaner.invoke(directByteBuffer);
		Class<?> aClass1 = returenValue.getClass();
		System.out.println("cleaner method return class = " + aClass1.getName());
		Method clean = aClass1.getMethod("clean");
		clean.setAccessible(true);
		clean.invoke(returenValue);

	}
}
