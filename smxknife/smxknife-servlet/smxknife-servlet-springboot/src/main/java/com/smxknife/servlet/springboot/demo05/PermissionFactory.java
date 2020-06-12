package com.smxknife.servlet.springboot.demo05;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2020/2/13
 */
@Component
public class PermissionFactory implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

	private Logger logger = LoggerFactory.getLogger(PermissionFactory.class);

	private PermissionModeFactory modeFactory = PermissionModeFactory.NONE;

	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		modeFactory.registerPermissionService((beanDefinitionRegistry));
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		modeFactory.createProxyFactoryBean(beanFactory, logger);
	}

	@Override
	public void setEnvironment(Environment environment) {
		modeFactory = PermissionModeFactory.getMode(environment.getProperty("permission.mode"));
	}
}
