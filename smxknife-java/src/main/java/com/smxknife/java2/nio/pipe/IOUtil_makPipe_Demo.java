package com.smxknife.java2.nio.pipe;

import sun.nio.ch.IOUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2020/10/24
 */
public class IOUtil_makPipe_Demo {
	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		Method makePipe = IOUtil.class.getDeclaredMethod("makePipe", boolean.class);
		makePipe.setAccessible(true);
		long invoke = (long)makePipe.invoke(null, false);
		System.out.println(invoke);
		int readFd = (int) (invoke >>> 32);
		int writeFd = (int) invoke;

		System.out.println(readFd);
		System.out.println(writeFd);
	}
}
