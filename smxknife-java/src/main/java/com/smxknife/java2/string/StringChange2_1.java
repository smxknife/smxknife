package com.smxknife.java2.string;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2021/5/6
 */
public class StringChange2_1 {
	public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, IOException {
		String str = "123";
		System.out.println(System.identityHashCode(str));

		// 这种虽然是多线程，但是采用的是一个引用地址
		new Thread(() -> {
			while (true) {
				try {
					System.out.printf("%s str = %s\r\n", "test1", str);
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "test1").start();

		new Thread(() -> {
			while (true) {
				try {
					System.out.printf("%s str = %s\r\n", "test2", str);
					TimeUnit.MILLISECONDS.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "test2").start();

		changeStringValue(str, "333".toCharArray());

		System.out.println(str);
		System.out.println(System.identityHashCode(str));
		System.in.read();

	}

	public static String directChange(String str, char[] newValue) {
		System.out.println("param str: " + System.identityHashCode(str));
		str = str.replace(str, new String(newValue));
		return str;
	}

	public static String changeStringValue(String str, char[] newValue) throws NoSuchFieldException, IllegalAccessException {
		System.out.println("param str: " + System.identityHashCode(str));
		final Field valueField = str.getClass().getDeclaredField("value");
		valueField.setAccessible(true);
		valueField.set(str, newValue);
		return str;
	}
}
