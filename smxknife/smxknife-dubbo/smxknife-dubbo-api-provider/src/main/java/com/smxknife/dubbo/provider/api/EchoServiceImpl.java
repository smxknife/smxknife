package com.smxknife.dubbo.provider.api;

import com.smxknife.dubbo.api.service.EchoService;
import org.apache.dubbo.rpc.RpcContext;

import java.time.LocalTime;

/**
 * @author smxknife
 * 2019/12/11
 */
public class EchoServiceImpl implements EchoService {
	@Override
	public String echo(String message) {
		return String.format("[ %15s ] Hello %s, request from consumer: %s", LocalTime.now(), message, RpcContext.getContext().getRemoteAddress());
	}
}
