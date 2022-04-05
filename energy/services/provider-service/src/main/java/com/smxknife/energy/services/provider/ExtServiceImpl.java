package com.smxknife.energy.services.provider;

import org.apache.dubbo.common.URL;

/**
 * @author smxknife
 * 2021/5/24
 */
public class ExtServiceImpl implements ExtService {
	@Override
	public void print() {
		System.out.println("ExtServiceImpl print");
	}

	@Override
	public void printInfo0(URL url) {
		System.out.println("ExtServiceImpl printInfo0");
	}

	@Override
	public void printInfo1(URL url) {
		System.out.println("ExtServiceImpl printInfo1");
	}
}
