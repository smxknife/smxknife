package com.smxknife.spring.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author smxknife
 * 2019/12/14
 */
public class SmxknifeNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		super.registerBeanDefinitionParser("application", new ApplicationConfigDefinitionParser());
		super.registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
	}
}
