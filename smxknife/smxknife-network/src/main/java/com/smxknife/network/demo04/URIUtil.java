package com.smxknife.network.demo04;

import java.net.URI;

/**
 * @author smxknife
 * 2019-02-14
 */
public class URIUtil {
	
	public static void output(URI uri) {
		System.out.println("-----------------------------");
		System.out.println("-- uri: " + uri);
		System.out.printf("-- scheme: %s\r\n", uri.getScheme());
		System.out.printf("-- path: %s\r\n", uri.getPath());
		System.out.printf("-- authority: %s\r\n", uri.getAuthority());
		System.out.printf("-- fragment: %s\r\n", uri.getFragment());
		System.out.printf("-- host: %s\r\n", uri.getHost());
		System.out.printf("-- port: %s\r\n", uri.getPort());
		System.out.printf("-- query: %s\r\n", uri.getQuery());
		System.out.printf("-- rawAuthority: %s\r\n", uri.getRawAuthority());
		System.out.printf("-- rawQuery: %s\r\n", uri.getRawQuery());
		System.out.printf("-- RawFragment: %s\r\n", uri.getRawFragment());
		System.out.printf("-- RawPath: %s\r\n", uri.getRawPath());
		System.out.printf("-- RawSchemeSpecificPart: %s\r\n", uri.getRawSchemeSpecificPart());
		System.out.printf("-- RawUserInfo: %s\r\n", uri.getRawUserInfo());
		System.out.printf("-- toString: %s\r\n", uri.toString());
		System.out.printf("-- toASCIIString: %s\r\n", uri.toASCIIString());
		System.out.println("-----------------------------");
	}
}
