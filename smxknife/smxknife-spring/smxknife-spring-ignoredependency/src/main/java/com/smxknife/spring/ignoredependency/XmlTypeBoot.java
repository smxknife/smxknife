package com.smxknife.spring.ignoredependency;

import com.smxknife.spring.ignoredependency.inter.InterfaceTest;
import com.smxknife.spring.ignoredependency.type.TypeTest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author smxknife
 * 2020/2/5
 */
public class XmlTypeBoot {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
		context.addBeanFactoryPostProcessor(new IgnoreAutowiringTypeProcessor());

		InterfaceTest it = context.getBean(InterfaceTest.class);
		TypeTest type = context.getBean(TypeTest.class);

		it.test();
		type.test();

	}
}
