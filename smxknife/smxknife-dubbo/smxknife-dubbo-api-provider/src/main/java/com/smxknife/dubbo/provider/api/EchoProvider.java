package com.smxknife.dubbo.provider.api;

import com.smxknife.dubbo.api.service.EchoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/11
 */
public class EchoProvider {
	public static void main(String[] args) throws IOException {
		ServiceConfig<EchoService> service = new ServiceConfig<>();
		service.setApplication(new ApplicationConfig("echo-api-provider"));
		service.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
		service.setInterface(EchoService.class);
		service.setRef(new EchoServiceImpl());
		service.export(); // 暴露服务
		System.out.println("echo-api-provider is running");
		System.in.read();
	}
}
