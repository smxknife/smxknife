package com.smxknife.dubbo.provider.xml.service.impl;

import com.smxknife.dubbo.provider.xml.service.SpiService;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;

/**
 * @author smxknife
 * 2019/12/12
 */
@Adaptive
public class OtherSpiService implements SpiService {
	@Override
	public void echo(URL url) {
		System.out.println("other spi service");
	}
}
