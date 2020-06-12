package com.smxknife.spring.ioc.anno;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

/**
 * @author smxknife
 * 2020/2/25
 */
@Configuration
public class AnnotationIoCBoot {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnnotationIoCBoot.class);
		AnnoModel bean = applicationContext.getBean(AnnoModel.class);
		System.out.println(bean);
	}
}
