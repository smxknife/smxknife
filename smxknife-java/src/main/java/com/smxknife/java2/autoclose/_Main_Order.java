package com.smxknife.java2.autoclose;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/10/5
 */
public class _Main_Order {
	public static void main(String[] args) {
		try(DataChannel channel = new DataChannel(); DataInput input = new DataInput(); ) {
			input.handle();
			channel.handle();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("从输出结果来看，是try里面的autoclose资源是从后往前进行逐个关闭");
	}
}
