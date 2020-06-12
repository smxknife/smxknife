package com.smxknife.servlet.springboot.demo05;

import org.slf4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/2/13
 */
public enum PermissionModeFactory {
	NONE {
		@Override
		void registerPermissionService(BeanDefinitionRegistry beanDefinitionRegistry) {
		}

		@Override
		void createProxyFactoryBean(ConfigurableListableBeanFactory beanFactory, Logger logger) {
		}
	},
	REGISTER {
		@Override
		void registerPermissionService(BeanDefinitionRegistry beanDefinitionRegistry) {
			RootBeanDefinition beanDefinition = new RootBeanDefinition(RegisterEdtPermissionService.class);
			beanDefinitionRegistry.registerBeanDefinition("edtPermissionService", beanDefinition);
		}

		@Override
		void createProxyFactoryBean(ConfigurableListableBeanFactory beanFactory, Logger logger) {
			ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
			proxyFactoryBean.setBeanFactory(beanFactory);

			PermissionProperties properties = beanFactory.getBean(PermissionProperties.class);

			AfterReturningAdvice afterReturningAdvice = new AfterReturningAdvice() {
				@Override
				public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
					String name = method.getName();
					if ("saveOrUpdate".equals(name) && Objects.nonNull(o)) {
						if (logger.isInfoEnabled()) {
							logger.info("regEquipmentService saveOrUpdate method returning, value = {}", o);
						}
						EdtPermissionService permissionService = beanFactory.getBean(EdtPermissionService.class);
						permissionService.handle(o, objects[2], objects[3], properties.getKey());
					}
				}
			};

			proxyFactoryBean.addAdvice(afterReturningAdvice);
			proxyFactoryBean.setInterfaces(RegEquipmentService.class);
			proxyFactoryBean.setTarget(beanFactory.getBean(RegEquipmentService.class));
			beanFactory.registerSingleton("regEquipmentService", proxyFactoryBean);
		}
	},
	KERNEL_TEST {
		@Override
		void registerPermissionService(BeanDefinitionRegistry beanDefinitionRegistry) {
		}

		@Override
		void createProxyFactoryBean(ConfigurableListableBeanFactory beanFactory, Logger logger) {
		}
	},
	KERNEL {
		@Override
		void registerPermissionService(BeanDefinitionRegistry beanDefinitionRegistry) {
			RootBeanDefinition uploadEnergyBeanDefinition = new RootBeanDefinition(UploadEnergyDataAop.class);
			beanDefinitionRegistry.registerBeanDefinition("uploadEnergyDataAop", uploadEnergyBeanDefinition);

			RootBeanDefinition uploadConfigBeanDefinition = new RootBeanDefinition(UploadEnergyDataAop.class);
			beanDefinitionRegistry.registerBeanDefinition("uploadConfigDataAop", uploadConfigBeanDefinition);
		}

		@Override
		void createProxyFactoryBean(ConfigurableListableBeanFactory beanFactory, Logger logger) {

		}
	};

	abstract void registerPermissionService(BeanDefinitionRegistry beanDefinitionRegistry);
	abstract void createProxyFactoryBean(ConfigurableListableBeanFactory beanFactory, Logger logger);

	public static PermissionModeFactory getMode(String mode) {
		if (StringUtils.isEmpty(mode)) {
			return NONE;
		}
		String upperName = mode.toUpperCase();
		PermissionModeFactory modeFactory = PermissionModeFactory.valueOf(upperName);
		if (null == modeFactory) {
			return NONE;
		}
		return modeFactory;
	}
}
