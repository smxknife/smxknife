package com.smxknife.patterns.proxy.demo2;

/**
 * @author smxknife
 * 2019/10/29
 */
public class _Run {
	public static void main(String[] args) {
		Proxy1Service proxy1Service = new Proxy1Service();
		Proxy2Service proxy2Service = new Proxy2Service();

		proxy1Service.doSomething();
		System.out.println("----------------");
		proxy2Service.doSomething();
	}
}
