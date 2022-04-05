package com.smxknife.dubbo.demo.ch02_anno.provider;

import com.smxknife.dubbo.demo.EchoService;
import org.apache.dubbo.config.annotation.Service;
import org.apache.dubbo.rpc.RpcContext;

/**
 * @author smxknife
 * 2020/11/7
 */
@Service
public class EchoServiceImpl implements EchoService {
	@Override
	public void echo(String message) {
		System.out.println("hello, " + message + RpcContext.getContext().getRemoteAddress());
	}
}
