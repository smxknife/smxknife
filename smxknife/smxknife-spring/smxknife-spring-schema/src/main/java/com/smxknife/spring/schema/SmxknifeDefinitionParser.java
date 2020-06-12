package com.smxknife.spring.schema;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * @author smxknife
 * 2019/12/14
 */
@Deprecated
@AllArgsConstructor
public class SmxknifeDefinitionParser implements BeanDefinitionParser {

	private final Class<?> beanClass;

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		return parse(element, parserContext, beanClass);
	}

	private BeanDefinition parse(Element element, ParserContext parserContext, Class<?> beanClass) {
		RootBeanDefinition beanDefinition = new RootBeanDefinition();
		beanDefinition.setBeanClass(beanClass);
		beanDefinition.setLazyInit(false);
		String name = element.getAttribute("name");
		beanDefinition.getPropertyValues().addPropertyValue("name", name);
		parserContext.getRegistry().registerBeanDefinition(name, beanDefinition);
		return beanDefinition;
	}
}
