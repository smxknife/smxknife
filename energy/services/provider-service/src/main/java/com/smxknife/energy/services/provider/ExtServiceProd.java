package com.smxknife.energy.services.provider;

import org.apache.dubbo.common.URL;

/**
 * @author smxknife
 * 2021/5/24
 */
public class ExtServiceProd implements ExtService {
	@Override
	public void print() {
		System.out.println("ExtServiceProd print");
	}

	@Override
	public void printInfo0(URL url) {
		System.out.println("ExtServiceProd printInfo0");
	}

	@Override
	public void printInfo1(URL url) {
		System.out.println("ExtServiceProd printInfo1");
	}
}
