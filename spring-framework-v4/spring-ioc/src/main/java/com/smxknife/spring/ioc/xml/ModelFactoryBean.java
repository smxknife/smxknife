package com.smxknife.spring.ioc.xml;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author smxknife
 * 2019/12/24
 */
public class ModelFactoryBean implements FactoryBean<Model> {
	@Override
	public Model getObject() throws Exception {
		return new Model();
	}

	@Override
	public Class<?> getObjectType() {
		return Model.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
