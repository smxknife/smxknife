package com.smxknife.network.demo04;

import java.io.IOException;
import java.net.URL;

/**
 * @author smxknife
 * 2019-02-14
 */
public class URLUtil {
	
	public static void output(URL url) throws IOException {
		System.out.println("-----------------------------");
		System.out.println("-- url: " + url);
		System.out.printf("-- content: %s\r\n", url.getContent());
		System.out.printf("-- path: %s\r\n", url.getPath());
		System.out.printf("-- authority: %s\r\n", url.getAuthority());
		System.out.printf("-- defaultPort: %s\r\n", url.getDefaultPort());
		System.out.printf("-- host: %s\r\n", url.getHost());
		System.out.printf("-- port: %s\r\n", url.getPort());
		System.out.printf("-- query: %s\r\n", url.getQuery());
		System.out.printf("-- file: %s\r\n", url.getFile());
		System.out.printf("-- protocol: %s\r\n", url.getProtocol());
		System.out.printf("-- ref: %s\r\n", url.getRef());
		System.out.printf("-- userInfo: %s\r\n", url.getUserInfo());
		System.out.printf("-- toString: %s\r\n", url.toString());
		System.out.printf("-- toExternalFrom: %s\r\n", url.toExternalForm());
		System.out.println("-----------------------------");
	}
}
