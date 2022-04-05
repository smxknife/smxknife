package com.smxknife.dubbo.demo.ch01_xml.provider;

import com.smxknife.dubbo.demo.EchoService;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author smxknife
 * 2020/11/7
 */
public class EchoServiceImpl implements EchoService {
	@Override
	public void echo(String message) {
		System.out.println("hello, " + message + RpcContext.getContext().getRemoteAddress());
	}
}
