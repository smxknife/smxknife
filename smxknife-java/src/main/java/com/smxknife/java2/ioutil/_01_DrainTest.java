package com.smxknife.java2.ioutil;

import sun.nio.ch.IOUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author smxknife
 * 2020/10/30
 */
public class _01_DrainTest {

	public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// 因为drain包隐藏了，所以通过反射获取
		Method makePipe = IOUtil.class.getMethod("makePipe", boolean.class);
		long invoke = (long)makePipe.invoke(null, false);
		int fd0 = (int)(invoke >>> 32);
		int fd1 = (int)invoke;


	}
}
