package com.smxknife.springboot.v2.learn._02_factorybean;

import com.smxknife.springboot.v2.learn.common.B;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author smxknife
 * 2021/2/3
 */
public class BFactoryBean implements FactoryBean<B> {
	@Override
	public B getObject() throws Exception {
		return new B();
	}

	@Override
	public Class<?> getObjectType() {
		return B.class;
	}
}
