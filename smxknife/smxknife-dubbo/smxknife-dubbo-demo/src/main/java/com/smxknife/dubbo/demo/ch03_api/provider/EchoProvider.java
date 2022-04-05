package com.smxknife.dubbo.demo.ch03_api.provider;

import com.smxknife.dubbo.demo.EchoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/11/7
 */
public class EchoProvider {
	public static void main(String[] args) throws IOException {
		ServiceConfig<EchoService> serviceConfig = new ServiceConfig<>();
		serviceConfig.setApplication(new ApplicationConfig("echo-api-provider"));
		serviceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
		serviceConfig.setInterface(EchoService.class); // 指定服务暴露接口
		serviceConfig.setRef(new EchoServiceImpl()); // 指定真是服务对象
		serviceConfig.export(); // 暴露服务
		System.out.println("echo-api-provier is running");
		System.in.read();
	}
}
