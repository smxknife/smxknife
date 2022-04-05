package com.smxknife.mybatis._01_xml;

import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;

/**
 * @author smxknife
 * 2021/6/27
 */
public class MyObjectFactory extends DefaultObjectFactory {
	public MyObjectFactory() {
		super();
	}

	@Override
	public <T> T create(Class<T> type) {
		System.out.println("MyObjectFactory create ｜ " + type.getName());
		return super.create(type);
	}

	@Override
	public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
		System.out.println("MyObjectFactory create ｜ " + type.getName());
		return super.create(type, constructorArgTypes, constructorArgs);
	}

	@Override
	protected Class<?> resolveInterface(Class<?> type) {
		System.out.println("MyObjectFactory resolveInterface ｜ " + type.getName());
		return super.resolveInterface(type);
	}

	@Override
	public <T> boolean isCollection(Class<T> type) {
		System.out.println("MyObjectFactory isCollection ｜ " + type.getName());
		return super.isCollection(type);
	}
}
