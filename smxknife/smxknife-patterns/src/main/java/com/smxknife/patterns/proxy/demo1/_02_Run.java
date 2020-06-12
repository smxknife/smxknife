package com.smxknife.patterns.proxy.demo1;

import java.lang.reflect.Proxy;

/**
 * @author smxknife
 * 2019/12/27
 */
public class _02_Run {
	public static void main(String[] args) {

		_02_Service service = new _02_ServiceImpl();
		_02_DynamicProxyHandler handler = new _02_DynamicProxyHandler(service);
		_02_Service proxyInstance = (_02_Service)Proxy.newProxyInstance(service.getClass().getClassLoader(), service.getClass().getInterfaces(), handler);
		Integer service1 = proxyInstance.service();
	}
}
