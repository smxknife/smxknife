package com.smxknife.dubbo.demo.ch03_api.consumer;

import com.smxknife.dubbo.demo.EchoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author smxknife
 * 2020/11/7
 */
public class EchoConsumer {
	public static void main(String[] args) {
		ReferenceConfig<EchoService> referenceConfig = new ReferenceConfig<>();
		referenceConfig.setApplication(new ApplicationConfig("echo-api-consumer"));
		referenceConfig.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
		referenceConfig.setInterface(EchoService.class);
		EchoService echoService = referenceConfig.get();
		echoService.echo("api");
	}
}
