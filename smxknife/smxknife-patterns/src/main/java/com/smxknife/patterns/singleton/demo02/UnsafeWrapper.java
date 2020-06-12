package com.smxknife.patterns.singleton.demo02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author smxknife
 * 2019-08-21
 */
public class UnsafeWrapper {

	public static Unsafe unsafe() {
		return UnsafeWrapperHolder.unsafe;
	}

	private static class UnsafeWrapperHolder {
		private static Unsafe unsafe = null;
		static {
			try {
				Field theUnsafeField = Unsafe.class.getDeclaredField("theUnsafe");
				theUnsafeField.setAccessible(true);
				unsafe = (Unsafe) theUnsafeField.get(null);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
}
