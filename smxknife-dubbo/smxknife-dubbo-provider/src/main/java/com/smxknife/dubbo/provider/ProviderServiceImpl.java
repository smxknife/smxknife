package com.smxknife.dubbo.provider;

import com.smxknife.dubbo.spi.ProviderService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author smxknife
 * 2021/5/24
 */
@DubboService(version = "1.0", timeout = 10, interfaceClass = ProviderService.class)
public class ProviderServiceImpl implements ProviderService {

	@Value("${server.port}")
	int port;


	@Override
	public String sayHi() {
		return "hi, i am " + port;
	}
}
