package com.smxknife.spring.ignoredependency;

import com.smxknife.spring.ignoredependency.inter.IgnoreInterface;
import com.smxknife.spring.ignoredependency.type.IgnoreType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * @author smxknife
 * 2020/2/5
 */
public class IgnoreAutowiringInterfaceProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		beanFactory.ignoreDependencyInterface(IgnoreInterface.class);
		beanFactory.ignoreDependencyInterface(IgnoreType.class);
	}
}
