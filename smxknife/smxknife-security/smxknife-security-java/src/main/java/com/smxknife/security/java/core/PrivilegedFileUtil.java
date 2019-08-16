package com.smxknife.security.java.core;

import java.io.File;
import java.io.IOException;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author smxknife
 * 2019-08-14
 */
public class PrivilegedFileUtil {

	public static boolean canRead(String fileName) {
		// TODO 没加任何异常
		File file = new File(fileName);
		return file.canRead();
	}

	public static void makeFile(String fileName) {
		File file = new File(fileName);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void doPrivilegedAction(final String fileName) {
		AccessController.doPrivileged(new PrivilegedAction<String>() {
			@Override
			public String run() {
				makeFile(fileName);
				return null;
			}
		});
	}
}
