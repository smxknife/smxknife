package com.smxknife.servlet.tomcat.demo01.initializer;


import com.smxknife.servlet.tomcat.demo01.servlet.HahaServlet;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.Set;

/**
 * @author smxknife
 * 2018-12-26
 */
@HandlesTypes(value = HahaServlet.class)
public class HahaServletContainerInitializer implements ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {

		System.out.println("----------------------");
		System.out.println(servletContext.getContextPath());
		System.out.println(servletContext.getServletContextName());
		System.out.println(servletContext.getClass());
		System.out.println(servletContext.getServerInfo());
		Enumeration<String> servletNames = servletContext.getServletNames();
		while (servletNames.hasMoreElements()) {
			System.out.println(servletNames.nextElement());
		}
		System.out.println("======================");
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

		servletContext.getServletRegistrations().forEach((k,v) -> {
			String k1 = k;
			ServletRegistration v1 = v;

			System.out.println("===========" + k1);
			System.out.println(v1.getRunAsRole());
			System.out.println(v1.getName());
			System.out.println(v1.getClassName());
			v1.getMappings().forEach(m -> {
				String m1 = m;
				System.out.println(m1);
			});
		});

		servletContext.getFilterRegistrations().forEach((k, v) -> {
			String k1 = k;
			FilterRegistration v1 = v;
			System.out.println("-------------------");
			System.out.println(k1);
			System.out.println(v1.getClassName());
			System.out.println(v1.getClass());
			System.out.println(v1.getName());
			v1.getServletNameMappings().forEach(m -> System.out.println("servlet : " + m));
			v1.getUrlPatternMappings().forEach(url -> System.out.println("url : " + url));
		});
	}
}
