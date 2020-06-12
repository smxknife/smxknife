package com.smxknife.dubbo.provider.xml.service;

import org.apache.dubbo.common.extension.SPI;

/**
 * @author smxknife
 * 2019/12/12
 */
@SPI("impl")
public interface PrintService {
	void printInfo();
}
