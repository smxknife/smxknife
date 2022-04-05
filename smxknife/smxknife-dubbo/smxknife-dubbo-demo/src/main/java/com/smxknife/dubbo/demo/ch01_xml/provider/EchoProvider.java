package com.smxknife.dubbo.demo.ch01_xml.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author smxknife
 * 2020/11/7
 */
public class EchoProvider {

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"ch01/echo-provider.xml"});
		context.start();

		System.in.read();
	}
}
