package com.smxknife.dubbo.provider.xml.service;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author smxknife
 * 2019/12/12
 */
@SPI("java")
public interface SpiService {
	@Adaptive
	void echo(URL url);
}
