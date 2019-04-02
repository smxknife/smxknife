package com.smxknife.network.demo04;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author smxknife
 * 2019-02-15
 */
public class URLDemo2 {
	public static void main(String[] args) throws MalformedURLException {
		URL url = new URL("news://aaa/bbb");
		System.out.println(url.getPath());
	}
}
