package com.smxknife.java2.io.file;

import java.io.File;
import java.io.FileFilter;

/**
 * @author smxknife
 * 2018/11/12
 */
public class MyFileFilter implements FileFilter {

	@Override
	public boolean accept(File pathname) {
		System.out.println(pathname);
		return true;
	}
}
