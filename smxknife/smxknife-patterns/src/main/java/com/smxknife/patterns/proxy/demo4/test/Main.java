package com.smxknife.patterns.proxy.demo4.test;

import com.smxknife.patterns.proxy.Service;
import com.smxknife.patterns.proxy.Service1Impl;

/**
 * @author smxknife
 * 2019/12/27
 */
public class Main {
	public static void main(String[] args) {
		Service instance = (Service)new TestHandler().getInstance(new Service1Impl());
		instance.doSomething();
	}
}
