package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2020/2/10
 */
@Component
public class Demo4BeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		configurableListableBeanFactory.ignoreDependencyInterface(Other4Service.class);
		configurableListableBeanFactory.ignoreDependencyType(Other4Service.class);
		configurableListableBeanFactory.ignoreDependencyType(Other4ServiceImpl.class);
	}
}
