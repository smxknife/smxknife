package com.smxknife.patterns.proxy.demo1;

import com.smxknife.patterns.proxy.Service;
import com.smxknife.patterns.proxy.Service1Impl;
import com.smxknife.patterns.proxy.Service2Impl;

import java.lang.reflect.Proxy;

/**
 * @author smxknife
 * 2019/10/29
 */
public class _01_Run {
	public static void main(String[] args) {

		Service impl1 = new Service1Impl();
		_01_ServiceInvocationHandler handler1 = new _01_ServiceInvocationHandler(impl1);
		Service service1 = (Service)Proxy.newProxyInstance(handler1.getClass().getClassLoader(), impl1.getClass().getInterfaces(), handler1);
		service1.doSomething();
		System.out.println("---------------------");

		Service impl2 = new Service2Impl();
		_01_ServiceInvocationHandler handler2 = new _01_ServiceInvocationHandler(impl2);
		Service service2 = (Service)Proxy.newProxyInstance(handler2.getClass().getClassLoader(), impl2.getClass().getInterfaces(), handler2);
		service2.doSomething();

	}
}
