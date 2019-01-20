package com.smxknife.servlet.tomcat.demo02;

import com.smxknife.servlet.tomcat.demo02.filter.Demo02Filter1;
import com.smxknife.servlet.tomcat.demo02.filter.Demo02Filter2;
import com.smxknife.servlet.tomcat.demo02.listener.*;
import com.smxknife.servlet.tomcat.demo02.servlet.Demo02Servlet1;
import com.smxknife.servlet.tomcat.demo02.servlet.Demo02Servlet2;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author smxknife
 * 2019-01-19
 */
public class Demo02ServletContainerInitializer implements ServletContainerInitializer {
	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
		// Servlet
		ServletRegistration.Dynamic servlet1 = servletContext.addServlet("demo02Servlet1", new Demo02Servlet1());
		servlet1.setLoadOnStartup(0);
		servlet1.addMapping("/demo02/serv1");

		ServletRegistration.Dynamic servlet2 = servletContext.addServlet("demo02Servlet2", Demo02Servlet2.class);
		servlet2.addMapping("/demo02/serv2");
		// Servlet

		// Filter
		FilterRegistration.Dynamic filter1 = servletContext.addFilter("demo02Filter1", new Demo02Filter1());
		filter1.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/demo02/serv1");

		FilterRegistration.Dynamic filter2 = servletContext.addFilter("demo02Filter2", new Demo02Filter2());
		filter2.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/demo02/serv2");
		// Filter

		// Listener
		servletContext.addListener(Demo02ServletContextAttributeListener.class.getName());
		servletContext.addListener(new Demo02ServletRequestListener());
		servletContext.addListener(Demo02RequestAttributeListener.class);
		Demo02HttpSessionListener listener = servletContext.createListener(Demo02HttpSessionListener.class);
		servletContext.addListener(listener);
		servletContext.addListener(new Demo02HttpSessionAttributeListener());
		servletContext.addListener(new Demo02HttpSessionIdListener());
		// Listener
	}
}
