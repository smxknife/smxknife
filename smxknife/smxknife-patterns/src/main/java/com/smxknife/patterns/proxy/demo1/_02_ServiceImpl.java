package com.smxknife.patterns.proxy.demo1;

/**
 * @author smxknife
 * 2019/12/27
 */
public class _02_ServiceImpl implements _02_Service {
	@Override
	public Integer service() {
		System.out.println(this.getClass().getCanonicalName() + " ...");
		return 1000;
	}
}
