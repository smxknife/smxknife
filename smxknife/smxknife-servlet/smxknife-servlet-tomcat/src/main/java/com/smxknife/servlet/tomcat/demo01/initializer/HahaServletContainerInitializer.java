package com.smxknife.servlet.tomcat.demo01.initializer;


import com.smxknife.servlet.tomcat.demo01.servlet.HahaServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.Set;

/**
 * @author smxknife
 * 2018-12-26
 */
@HandlesTypes(value = HahaServlet.class)
public class HahaServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
		System.out.println("servlet container nitializer onStarup");
		System.out.println(set);
		set.forEach(c -> {
			if (!(Modifier.isInterface(c.getModifiers()) || Modifier.isAbstract(c.getModifiers()))) {
				ServletRegistration.Dynamic dynamic = servletContext.addServlet(c.getName(), (Class<? extends HahaServlet>)c);
				String pattern = "/" + c.getSimpleName().substring(0, 1).toLowerCase() + c.getSimpleName().substring(1);
				dynamic.addMapping(pattern);
				System.out.println("finish");
				System.out.println(pattern);
			}
		});
	}
}
