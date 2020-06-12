package com.smxknife.spring.manual;

import com.smxknife.spring.manual.annotation.Autowired;
import com.smxknife.spring.manual.annotation.Controller;
import com.smxknife.spring.manual.annotation.RequestMapping;
import com.smxknife.spring.manual.annotation.Service;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

/**
 * @author smxknife
 * 2019/12/30
 */
@Deprecated
public class DispatcherServlet_V0 extends HttpServlet {

	private Map<String, Object> mapping = new HashMap<>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			doDispatch(req, resp);
		} catch (Exception e) {
			resp.getWriter().write("500 Server Error, Message is " + e.getMessage());
		}
	}

	private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		uri = uri.replace(contextPath, "").replaceAll("/+", "/");
		if (!mapping.containsKey(uri)) {
			resp.getWriter().write("404 Not Found!");
			return;
		}

		Method method = (Method) this.mapping.get(uri);
		Map<String, String[]> parameters = req.getParameterMap();
		System.out.println("parameters = " + parameters);
		method.invoke(this.mapping.get(method.getDeclaringClass().getName()), new Object[]{parameters.get("name")[0], req, resp});
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("------------ " + Thread.currentThread().getName());
		InputStream is = null;

		try {
			Properties configContext = new Properties();
			is = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
			configContext.load(is);
			String scanPackage = configContext.getProperty("scanPackage");
			System.out.println("-------- scanPackage = " + scanPackage);
			doScanner(scanPackage);

			Map<String, Object> methodMapping = new HashMap<>();
			for (String className : mapping.keySet()) {
				if (!className.contains(".")) {continue;}
				Class<?> clazz = Class.forName(className);
				if (clazz.isAnnotationPresent(Controller.class)) {
					mapping.put(className, clazz.newInstance());
					String baseUrl = "";
					if (clazz.isAnnotationPresent(RequestMapping.class)) {
						RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
						baseUrl = requestMapping.value();
					}
					Method[] methods = clazz.getMethods();
					for (Method method : methods) {
						if (!method.isAnnotationPresent(RequestMapping.class)) {continue;}
						RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
						String uri = (baseUrl + "/" + requestMapping.value()).replaceAll("/+", "/");
						methodMapping.put(uri, method);
						System.out.printf("Mapped uri [%s] for method [%s] \r\n", uri, method.getName());
					}
				} else if (clazz.isAnnotationPresent(Service.class)) {
					Service service = clazz.getAnnotation(Service.class);
					String serviceName = service.value();
					if ("".equals(serviceName)) {
						serviceName = clazz.getName();
					}
					Object instance = clazz.newInstance();
					mapping.put(serviceName, instance);
					for (Class<?> i : clazz.getInterfaces()) {
						mapping.put(i.getName(), instance);
					}
				} else {
					continue;
				}
			}

			mapping.putAll(methodMapping);

			for (Object object : mapping.values()) {
				if (Objects.isNull(object)) {
					continue;
				}

				Class<?> clazz = object.getClass();
				if (clazz.isAnnotationPresent(Controller.class)) {
					Field[] fields = clazz.getDeclaredFields();
					for (Field field : fields) {
						if (!field.isAnnotationPresent(Autowired.class)) {
							continue;
						}
						Autowired autowired = field.getAnnotation(Autowired.class);
						String beanName = autowired.value();
						if ("".equals(beanName)) {
							beanName = field.getType().getName();
						}
						field.setAccessible(true);

						field.set(mapping.get(clazz.getName()), mapping.get(beanName));
					}
				}
			}

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		finally {
			if (Objects.nonNull(is)) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Manual MVC Framework is init");
	}

	private void doScanner(String scanPackage) {
		URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
		File classDir = new File(url.getFile());
		for (File file : classDir.listFiles()) {
			if (file.isDirectory()) {
				doScanner(scanPackage + "." + file.getName());
			} else {
				if (!file.getName().endsWith(".class")) {
					continue;
				}
				String className = (scanPackage) + "." + file.getName().replace(".class", "");
				mapping.put(className, null);
			}
		}
	}
}
