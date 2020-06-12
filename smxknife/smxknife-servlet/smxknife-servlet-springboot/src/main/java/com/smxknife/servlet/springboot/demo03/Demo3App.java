package com.smxknife.servlet.springboot.demo03;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author smxknife
 * 2020/2/6
 */
@SpringBootApplication
public class Demo3App implements BeanFactoryPostProcessor {
	public static void main(String[] args) {
		SpringApplication.run(Demo3App.class, args);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		beanFactory.ignoreDependencyType(IgnoreType.class);
		beanFactory.ignoreDependencyInterface(IgnoreInterface.class);
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public IgnoreInterface ignoreInterface() {
		return new IgnoreInterfaceImpl();
	}

	@Bean(autowire = Autowire.BY_TYPE)
	public IgnoreType ignoreType() {
		return new IgnoreType();
	}

}
