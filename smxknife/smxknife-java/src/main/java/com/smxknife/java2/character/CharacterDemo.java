package com.smxknife.java2.character;

import java.io.UnsupportedEncodingException;

/**
 * @author smxknife
 * 2018/11/13
 */
public class CharacterDemo {
	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "好";
		utf8(str);
		gbk(str);
		gb2312(str);
		defaultChar(str);
		iso88591(str);
		utf16(str);
	}

	private static void utf16(String str) throws UnsupportedEncodingException {
		charset(str, "utf16");
	}

	private static void defaultChar(String str) throws UnsupportedEncodingException {
		charset(str, null);
	}

	private static void iso88591(String str) throws UnsupportedEncodingException {
		charset(str, "iso8859-1");
	}

	private static void utf8(String str) throws UnsupportedEncodingException {
		charset(str, "utf8");
	}

	private static void gbk(String str) throws UnsupportedEncodingException {
		charset(str, "gbk");
	}

	private static void gb2312(String str) throws UnsupportedEncodingException {
		charset(str, "gb2312");
	}

	private static void charset(String str, String charset) throws UnsupportedEncodingException {
		byte[] bytes = null;
		if (charset == null || "".equals(charset.trim())) {
			bytes = str.getBytes();
		} else {
			bytes = str.getBytes(charset);
		}
		System.out.printf("%-5s", str);
		System.out.println(charset + " length " + bytes.length);
		for (int i = 0; i < bytes.length; i++) {
			System.out.printf("%-4s", bytes[i]);
		}
		System.out.println();
		System.out.println("----------------------");
	}
}
