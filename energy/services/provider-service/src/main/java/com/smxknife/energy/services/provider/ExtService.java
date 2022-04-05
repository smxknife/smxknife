package com.smxknife.energy.services.provider;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author smxknife
 * 2021/5/24
 */
@SPI("impl") // 默认实现是impl，在META-INF/dubbo/internal/com.smxknife.energy.services.provider.ExtSPIService里面
public interface ExtService {

	void print();

	@Adaptive
	void printInfo0(URL url);

	@Adaptive({"a1", "a2"})
	void printInfo1(URL url);
}
