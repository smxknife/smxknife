package com.smxknife.energy.services.provider;

import com.smxknife.energy.services.spi.TagSayHiService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;

/**
 * @author smxknife
 * 2021/5/24
 */
@DubboService(version = "1.0", interfaceClass = TagSayHiService.class)
public class TagSayHiServiceImpl implements TagSayHiService {
	
	@Value("${server.port}")
	int port;

	@Override
	public String sayHi() {
		return "hi, I am " + port;
	}

}
