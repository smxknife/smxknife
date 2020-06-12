package com.smxknife.spring.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author smxknife
 * 2019/12/16
 */
public class IoCBoot {
	public static void main(String[] args) {
		ApplicationContext context = new FileSystemXmlApplicationContext("bean.xml");
		Model model = (Model) context.getBean("myModel");
	}
}
