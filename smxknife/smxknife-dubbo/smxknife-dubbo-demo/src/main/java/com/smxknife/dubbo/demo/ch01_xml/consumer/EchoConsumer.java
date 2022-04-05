package com.smxknife.dubbo.demo.ch01_xml.consumer;

import com.smxknife.dubbo.demo.EchoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author smxknife
 * 2020/11/7
 */
public class EchoConsumer {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"ch01/echo-consumer.xml"});
		context.start();

		EchoService echoService = (EchoService) context.getBean("echoService");
		echoService.echo("hi,,,");
	}
}
