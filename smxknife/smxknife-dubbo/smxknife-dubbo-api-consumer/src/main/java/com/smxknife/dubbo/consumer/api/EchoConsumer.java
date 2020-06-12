package com.smxknife.dubbo.consumer.api;

import com.smxknife.dubbo.api.service.EchoService;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

/**
 * @author smxknife
 * 2019/12/11
 */
public class EchoConsumer {
	public static void main(String[] args) {
		ReferenceConfig<EchoService> reference = new ReferenceConfig<>();
		reference.setApplication(new ApplicationConfig("echo-api-consumer"));
		reference.setRegistry(new RegistryConfig("zookeeper://localhost:2181"));
		reference.setInterface(EchoService.class);
		EchoService echoService = reference.get();
		String echo = echoService.echo("Hello Api");
		System.out.println(echo);

	}
}
