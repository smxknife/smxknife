package com.smxknife.network.demo04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import static com.smxknife.network.demo04.URLUtil.output;

/**
 * @author smxknife
 * 2019-02-15
 */
public class URLDemo4 {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.smxknife.com/2018/03/22/SpringBoot%E4%B9%8B%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E4%BC%98%E5%85%88%E7%BA%A7/");
		output(url);

		URLConnection connection = url.openConnection();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		}

		System.out.printf("contentLengthLong: %s\r\n", connection.getContentLengthLong());
		System.out.printf("contentLength: %s\r\n", connection.getContentLength());
		System.out.printf("contentType: %s\r\n", connection.getContentType());
		System.out.printf("connectTimeout: %s\r\n", connection.getConnectTimeout());
		System.out.printf("contentEncoding: %s\r\n", connection.getContentEncoding());
		// TODO 还有很多元数据，不一一输出
	}
}
