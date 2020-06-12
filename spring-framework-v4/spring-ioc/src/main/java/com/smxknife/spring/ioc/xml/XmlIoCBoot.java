package com.smxknife.spring.ioc.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author smxknife
 * 2019/12/16
 */
public class XmlIoCBoot {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("classpath:bean-${user.country1:default}.xml");
		Model model = (Model) context.getBean("myModel");
		System.out.println(model.getName());
		System.out.println(model);

//		System.out.println("FactoryBean产生的Bean-----------------");
//		Object bean1 = context.getBean("modelFactoryBean");
//		System.out.println(bean1);
//
//		Object bean2 = context.getBean("modelFactoryBean");
//		System.out.println(bean2);
//
//		System.out.println("FactoryBean产生的Bean-------end----------");
//
//		Object factoryBean = context.getBean("&modelFactoryBean");
//		System.out.println(factoryBean.getClass().getCanonicalName());

	}
}
