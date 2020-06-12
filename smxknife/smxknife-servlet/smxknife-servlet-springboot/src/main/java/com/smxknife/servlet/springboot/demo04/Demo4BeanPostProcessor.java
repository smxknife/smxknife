package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author smxknife
 * 2020/2/11
 */
@Component
public class Demo4BeanPostProcessor implements BeanPostProcessor {
	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
		// System.out.println("--------" + o.getClass());
		// System.out.println("--------" + s);

		if (o instanceof BeanInitAndDestroy) {
			System.out.println("----- BeanPostProcessor postProcessBeforeInitialization");
		}

		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
		// System.out.println("========" + o.getClass());
		// System.out.println("========" + s);

		if (o instanceof BeanInitAndDestroy) {
			System.out.println("----- BeanPostProcessor postProcessAfterInitialization");
		}

		return o;
	}
}
