package com.smxknife.dubbo.consumer.annotation;

import com.smxknife.dubbo.consumer.annotation.action.EchoConsumer;
import com.smxknife.dubbo.consumer.annotation.config.ConsumerConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author smxknife
 * 2019/12/11
 */
public class ConsumerAnnotationBoot {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
		context.start();

		EchoConsumer echoConsumer = context.getBean(EchoConsumer.class);
		String echo = echoConsumer.echo("Hello, Annotation");
		System.out.println(echo);

	}
}
