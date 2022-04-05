package com.smxknife.springboot.v2.learn._01_beanfactorypostprocessor;

import com.smxknife.springboot.v2.learn.common.A;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author smxknife
 * 2021/2/3
 */
public class _01_Main {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("_01/bean.xml");
		A bean = context.getBean(A.class);
		System.out.println(bean);
	}
}
