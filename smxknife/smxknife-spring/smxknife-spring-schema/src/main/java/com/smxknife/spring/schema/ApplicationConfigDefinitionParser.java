package com.smxknife.spring.schema;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.w3c.dom.Element;

/**
 * @author smxknife
 * 2019/12/14
 */
public class ApplicationConfigDefinitionParser extends AbstractSingleBeanDefinitionParser {

	@Override
	protected Class<?> getBeanClass(Element element) {
		return ApplicationConfig.class;
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		String name = element.getAttribute("name");
		String version = element.getAttribute("version");
		String description = element.getAttribute("description");
		builder.addPropertyValue("version", version);
		builder.addPropertyValue("name", name);
		builder.addPropertyValue("description", description);
	}
}
