package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author smxknife
 * 2020/2/10
 */
@Component
public class AnnotationConfigurationScanner implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
		RootBeanDefinition rootBeanDefinition = new RootBeanDefinition(Demo4InterfaceImpl.class);
		beanDefinitionRegistry.registerBeanDefinition("demo4Interface", rootBeanDefinition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
		Map<String, Object> beansWithAnnotation = configurableListableBeanFactory.getBeansWithAnnotation(Service.class);
		System.out.println("================");
		System.out.println(beansWithAnnotation);
	}
}
