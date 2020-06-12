package com.smxknife.patterns.proxy.demo2;

import com.smxknife.patterns.proxy.Service;
import com.smxknife.patterns.proxy.Service1Impl;

/**
 * @author smxknife
 * 2019/10/29
 */
public class Proxy1Service implements Service {

	private Service service = new Service1Impl();

	@Override
	public void doSomething() {
		preDo();
		service.doSomething();
		postDo();
	}

	private void postDo() {
		System.out.println("proxy1 postDo");
	}

	private void preDo() {
		System.out.println("proxy1 preDo");
	}
}
