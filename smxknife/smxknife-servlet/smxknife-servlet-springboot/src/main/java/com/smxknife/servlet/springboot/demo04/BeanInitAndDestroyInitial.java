package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2020/2/12
 */
@Component
public class BeanInitAndDestroyInitial extends InstantiationAwareBeanPostProcessorAdapter {
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof BeanInitAndDestroy) {
			System.out.println("----- InstantiationAwareBeanPostProcessorAdapter postProcessBeforeInitialization");
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof BeanInitAndDestroy) {
			System.out.println("----- InstantiationAwareBeanPostProcessorAdapter postProcessAfterInitialization");
		}
		return bean;
	}


}
