package com.smxknife.lock.mysql;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author smxknife
 * 2021/6/11
 */
public class GracefulClose {

	public static void close(AutoCloseable... closeables) {
		Arrays.asList(closeables).stream().filter(Objects::nonNull).forEach(closeable -> {
			try {
				closeable.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
