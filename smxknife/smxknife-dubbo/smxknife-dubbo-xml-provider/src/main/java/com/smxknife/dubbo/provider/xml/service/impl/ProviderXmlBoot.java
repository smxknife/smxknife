package com.smxknife.dubbo.provider.xml.service.impl;

import com.smxknife.dubbo.provider.xml.service.PrintService;
import org.apache.dubbo.common.extension.ExtensionLoader;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/11
 */
public class ProviderXmlBoot {
	public static void main(String[] args) throws IOException {
		// 指定服务暴露配置文件
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/echo-provider.xml"});
		// 启动spring容器并暴露服务
		context.start();

		// spi
		PrintService printService = ExtensionLoader.getExtensionLoader(PrintService.class).getDefaultExtension();
		printService.printInfo();



		System.in.read();
	}
}
