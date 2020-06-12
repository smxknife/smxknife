package com.smxknife.servlet.springboot.demo04;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

/**
 * @author smxknife
 * 2020/2/12
 */
public class Demo4AnnoImportRegistrator implements ImportBeanDefinitionRegistrar, EnvironmentAware {

	private Environment environment;

	@Override
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	@Override
	public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
		// 创建一个classpath的scanner
		ClassPathScanningCandidateComponentProvider scanner = getScanner();
		// 添加一个扫描的拦截器，只让被TestUtil注解装饰的class过
		scanner.addIncludeFilter(new AnnotationTypeFilter(Demo4Anno.class));
		for(BeanDefinition beanDefinition : scanner.findCandidateComponents(ClassUtils.getPackageName(annotationMetadata.getClassName()))){
			// 对于扫描出来的BeanDefinition，如果class是TestInferface
			if(beanDefinition.getBeanClassName().equals(Demo4AnnoInterface.class.getCanonicalName())){
				// 就将实现类TestImpl当做bean class 添加到beanDefinitionRegistry
				// 方便后面容器启动创建bean的时候创建出来
				beanDefinition.setBeanClassName(Demo4AnnoInterfaceImpl.class.getCanonicalName());
				beanDefinitionRegistry.registerBeanDefinition(ClassUtils.getShortName(Demo4AnnoInterface.class), beanDefinition);
			}
		}
	}

	private ClassPathScanningCandidateComponentProvider getScanner(){
		// 创建一个class path scanner
		return new ClassPathScanningCandidateComponentProvider(false, environment){
			@Override
			protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
				// 只要候选的class是个interface就让他过
				return beanDefinition.getMetadata().isInterface();
			}
		};
	}
}
