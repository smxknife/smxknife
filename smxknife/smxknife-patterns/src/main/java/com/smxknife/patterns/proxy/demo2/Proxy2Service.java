package com.smxknife.patterns.proxy.demo2;

import com.smxknife.patterns.proxy.Service;
import com.smxknife.patterns.proxy.Service2Impl;

/**
 * @author smxknife
 * 2019/10/29
 */
public class Proxy2Service implements Service {

	private Service service = new Service2Impl();

	@Override
	public void doSomething() {
		preDo();
		service.doSomething();
		postDo();
	}

	private void postDo() {
		System.out.println("proxy2 postDo");
	}

	private void preDo() {
		System.out.println("proxy2 preDo");
	}
}
