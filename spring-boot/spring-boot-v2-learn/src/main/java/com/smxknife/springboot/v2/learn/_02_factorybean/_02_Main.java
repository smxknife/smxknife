package com.smxknife.springboot.v2.learn._02_factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author smxknife
 * 2021/2/3
 */
public class _02_Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("_02/bean.xml");
		Object o1 = context.getBean("bFactoryBean");
		System.out.println(o1);

		Object o2 = context.getBean("&bFactoryBean");
		System.out.println(o2);

	}
}
