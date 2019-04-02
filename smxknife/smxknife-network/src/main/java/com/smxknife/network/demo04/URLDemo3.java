package com.smxknife.network.demo04;

import java.io.IOException;
import java.net.URL;

import static com.smxknife.network.demo04.URLUtil.output;

/**
 * @author smxknife
 * 2019-02-15
 */
public class URLDemo3 {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.smxknife.com/2018/03/22/SpringBoot%E4%B9%8B%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E4%BC%98%E5%85%88%E7%BA%A7/");
		output(url);

	}
}
