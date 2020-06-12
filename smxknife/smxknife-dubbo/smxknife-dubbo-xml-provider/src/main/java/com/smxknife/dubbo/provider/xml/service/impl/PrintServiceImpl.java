package com.smxknife.dubbo.provider.xml.service.impl;

import com.smxknife.dubbo.provider.xml.service.PrintService;

/**
 * @author smxknife
 * 2019/12/12
 */
public class PrintServiceImpl implements PrintService {

	public PrintServiceImpl() {
		System.out.println("construct printServiceImpl");
	}

	@Override
	public void printInfo() {
		System.out.println(this.getClass().getCanonicalName() + " print info");
	}
}
