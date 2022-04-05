package com.smxknife.springboot.v2.learn._03_AutowireCapableBeanFactory;

import com.smxknife.springboot.v2.learn.common.C;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author smxknife
 * 2021/2/6
 */
public class _03_Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("_03/bean.xml");
		AutowireCapableBeanFactory capableBeanFactory = context.getAutowireCapableBeanFactory();
		C c = new C();
		System.out.println("c = " + c);
		c.test();
//		Object c1 = capableBeanFactory.configureBean(c, "a");
//		System.out.println("c1 = " + c1);
//		((C)c1).test();
		C c2 = (C) capableBeanFactory.createBean(C.class, AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
		System.out.println("c2 = " + c2);
		((C)c2).test();

		C c3 = context.getBean(C.class);
		System.out.println(c3);


	}
}
