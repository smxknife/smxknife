package com.smxknife.dubbo.consumer.xml;

import com.smxknife.dubbo.api.service.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author smxknife
 * 2019/12/11
 */
public class ConsumerXmlBoot {
	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/echo-consumer.xml");
		context.start();

		EchoService echoService = (EchoService) context.getBean("echoService");
		String status = echoService.echo("I'am consumer.");
		System.out.println(status);
		System.in.read();
	}
}
