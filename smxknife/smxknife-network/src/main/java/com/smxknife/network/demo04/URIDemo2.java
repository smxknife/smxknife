package com.smxknife.network.demo04;

import java.net.URI;

import static com.smxknife.network.demo04.URIUtil.output;

/**
 * @author smxknife
 * 2019-02-14
 */
public class URIDemo2 {
	public static void main(String[] args) {
		URI uri = URI.create("aaa://shaoy:pwd@aut:8080/ppp+-(())/ccc/ddd?vvv=1中&222=1#fff");
		output(uri);
	}
}
