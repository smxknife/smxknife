package com.smxknife.dubbo.provider.xml.service.impl;

import com.smxknife.dubbo.provider.xml.service.SpiService;
import org.apache.dubbo.common.URL;

/**
 * @author smxknife
 * 2019/12/12
 */
public class SpringSpiService implements SpiService {
	@Override
	public void echo(URL url) {
		System.out.println("spring spi service");
	}
}
