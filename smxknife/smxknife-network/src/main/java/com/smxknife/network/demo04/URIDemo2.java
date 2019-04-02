package com.smxknife.network.demo04;

import java.net.URI;

import static com.smxknife.network.demo04.URIUtil.output;

/**
 * @author smxknife
 * 2019-02-14
 */
public class URIDemo2 {
	public static void main(String[] args) {
		URI uri = URI.create("/part1/part2");
		output(uri);

		uri = URI.create("aaa://shaoy@aaa/ppp/ccc/ddd?vvv=1&222=1#fff");
		output(uri);
	}
}
