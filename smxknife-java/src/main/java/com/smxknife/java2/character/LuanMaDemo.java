package com.smxknife.java2.character;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author smxknife
 * 2020/10/14
 */
public class LuanMaDemo {
	public static void main(String[] args) {

		String string = "你";
		utf8Decode(string);
		gb2312Decode(string);
		gbkDecode(string);
	}

	private static void gbkDecode(String string) {
		System.out.println("// 使用GBK解码 ---------------------");
		output("US-ASCII", "GBK", new String(string.getBytes(StandardCharsets.US_ASCII), Charset.forName("GBK")), string.getBytes(StandardCharsets.US_ASCII).length);
		output("ISO-8859-1", "GBK", new String(string.getBytes(StandardCharsets.ISO_8859_1), Charset.forName("GBK")), string.getBytes(StandardCharsets.ISO_8859_1).length);
		output("GB2312", "GBK", new String(string.getBytes(Charset.forName("GB2312")), Charset.forName("GBK")), string.getBytes(Charset.forName("GB2312")).length);
		output("GBK", "GBK", new String(string.getBytes(Charset.forName("GBK")), Charset.forName("GBK")), string.getBytes(Charset.forName("GBK")).length);
		output("UTF-8", "GBK", new String(string.getBytes(Charset.forName("UTF-8")), Charset.forName("GBK")), string.getBytes(StandardCharsets.UTF_8).length);
		output("UTF-16", "GBK", new String(string.getBytes(StandardCharsets.UTF_16), Charset.forName("GBK")), string.getBytes(StandardCharsets.UTF_16).length);
	}

	private static void gb2312Decode(String string) {
		System.out.println("// 使用GB2312解码 ---------------------");
		output("US-ASCII", "GB2312", new String(string.getBytes(StandardCharsets.US_ASCII), Charset.forName("GB2312")), string.getBytes(StandardCharsets.US_ASCII).length);
		output("ISO-8859-1", "GB2312", new String(string.getBytes(StandardCharsets.ISO_8859_1), Charset.forName("GB2312")), string.getBytes(StandardCharsets.ISO_8859_1).length);
		output("GB2312", "GB2312", new String(string.getBytes(Charset.forName("GB2312")), Charset.forName("GB2312")), string.getBytes(Charset.forName("GB2312")).length);
		output("GBK", "GB2312", new String(string.getBytes(Charset.forName("GBK")), Charset.forName("GB2312")), string.getBytes(Charset.forName("GBK")).length);
		output("UTF-8", "GB2312", new String(string.getBytes(Charset.forName("UTF-8")), Charset.forName("GB2312")), string.getBytes(StandardCharsets.UTF_8).length);
		output("UTF-16", "GB2312", new String(string.getBytes(StandardCharsets.UTF_16), Charset.forName("GB2312")), string.getBytes(StandardCharsets.UTF_16).length);
	}

	private static void utf8Decode(String string) {
		System.out.println("// 使用UTF-8解码 ---------------------");
		output("US-ASCII", "UTF-8", new String(string.getBytes(StandardCharsets.US_ASCII)), string.getBytes(StandardCharsets.US_ASCII).length);
		output("ISO-8859-1", "UTF-8", new String(string.getBytes(StandardCharsets.ISO_8859_1)), string.getBytes(StandardCharsets.ISO_8859_1).length);
		output("GB2312", "UTF-8", new String(string.getBytes(Charset.forName("GB2312"))), string.getBytes(Charset.forName("GB2312")).length);
		output("GBK", "UTF-8", new String(string.getBytes(Charset.forName("GBK"))), string.getBytes(Charset.forName("GBK")).length);
		output("UTF-8", "UTF-8", new String(string.getBytes(Charset.forName("UTF-8"))), string.getBytes(StandardCharsets.UTF_8).length);
		output("UTF-16", "UTF-8", new String(string.getBytes(StandardCharsets.UTF_16)), string.getBytes(StandardCharsets.UTF_16).length);

	}

	private static void output(String cha, String to, String str, int byteLength) {
		System.out.printf("%s -> %s | 【%s】length = %s\r\n", cha, to, str, byteLength);
	}
}
