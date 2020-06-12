package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author smxknife
 * 2020/2/12
 */
public class BeanInitAndDestroy implements InitializingBean, DisposableBean, BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {

	@Autowired
	private Other4Service other4Service;

	public BeanInitAndDestroy() {
		System.out.println("----- construct, other4=" + other4Service);
	}

	public void init() {
		System.out.println("----- init, other4=" + other4Service);
	}

	public void des() {
		System.out.println("----- des, other4=" + other4Service);
	}

	@PostConstruct
	public void postConstruct() {
		System.out.println("----- postConstruct, other4=" + other4Service);
	}

	@PreDestroy
	public void preDestroy() {
		System.out.println("----- preDestroy, other4=" + other4Service);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("----- afterPropertiesSet, other4=" + other4Service);
	}

	@Override
	public void destroy() throws Exception {
		System.out.println("----- destroy, other4=" + other4Service);
	}

	@Override
	public void setBeanName(String s) {
		System.out.println("----- setBeanName, name = " + s);
	}

	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		System.out.println("----- setBeanClassLoader, classLoader = " + classLoader);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("----- setBeanFactory, beanFactory = " + beanFactory);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("----- setApplicationContext, applicationContext = " + applicationContext);
	}
}
