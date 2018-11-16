package com.smxknife.java2.io.file;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 * @author smxknife
 * 2018/11/12
 */
public class DirFilter implements FilenameFilter {

	private Pattern pattern;

	public DirFilter(String regex) {
		pattern = Pattern.compile(regex);
	}

	@Override
	public boolean accept(File dir, String name) {
		System.out.println(">>> " + name);
		return pattern.matcher(name).matches();
	}
}
