package com.smxknife.drools.demo02.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author smxknife
 * 2020/6/15
 */
public class StringJoinUtil {

	private final static SimpleDateFormat sdfOne = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTwo = new SimpleDateFormat("yyyy-MM");
	private final static SimpleDateFormat sdfThree = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 日期转换
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return sdfOne.format(date);
	}

	public static String dateToStringTwo(Date date) {
		return sdfTwo.format(date);
	}

	public static String dateToStringThree(Date date) {
		return sdfThree.format(date);
	}
}
