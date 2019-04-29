package com.smxknife.network.demo05;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2019-02-15
 */
public class URLEncoderDemo {
	public static void main(String[] args) {
		List<String> urls = new ArrayList<>();
		List<String> encodedUrls = new ArrayList<>();
		init(urls);
		encodeOutput(urls, encodedUrls);
		System.out.println(urls);
		System.out.println(encodedUrls);
	}

	private static void encodeOutput(List<String> urls, List<String> encodeList) {
		urls.stream().forEach(url -> {
			try {
				url = URLEncoder.encode(url, "UTF-8");
				encodeList.add(url);
				System.out.println(url);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		});
	}

	private static void init(List<String> urls) {
		urls.add("this string has spaces");
		urls.add("this*string*has*asterisks");
		urls.add("this%string%has%percent%signs");
		urls.add("this+string+has+pluses");
		urls.add("this/string/has/slashes");
		urls.add("this\"string\"has\"quote\"marks");
		urls.add("this:string:has:colons");
		urls.add("this~string~has~tildes");
		urls.add("this(string)has(parentheses)");
		urls.add("this.string.has.periods");
		urls.add("this=string=has=equals=signs");
		urls.add("this&string&has&ampersands");
		urls.add("thiséstringéhasénon-ASCII characters");
	}
}
