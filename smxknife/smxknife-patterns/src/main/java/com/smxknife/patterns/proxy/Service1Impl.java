package com.smxknife.patterns.proxy;

/**
 * @author smxknife
 * 2019/10/29
 */
public class Service1Impl implements Service {
	@Override
	public void doSomething() {
		System.out.println("service - 1 impl doSomething");
	}
}
