package com.smxknife.spring.manual;

import com.smxknife.spring.manual.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author smxknife
 * 2019/12/30
 */
public class DispatcherServlet extends HttpServlet {

	// IoC容器简化版
	private Map<String, Object> ioc = new HashMap<>();
	// application.properties配置文件中的内容
	private Properties contextConfig = new Properties();
	// 保存扫描到的所有类名
	private List<String> classNames = new ArrayList<>();
	// url和Method对应关系
	// private Map<String, Method> handlerMapping = new HashMap<>();
	private List<Handler> handlerMapping = new ArrayList<>();


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

		Handler handler = getHandler(req);
		if (Objects.isNull(handler)) {
			resp.getWriter().write("404 Not Found!");
			return;
		}

		// 形参列表
		Class<?>[] paramTypes = handler.getParamTypes();
		Object[] paramValues = new Object[paramTypes.length];

		Map<String, String[]> parameterMap = req.getParameterMap();

		for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
			String value = Arrays.toString(entry.getValue()).replaceAll("\\[|\\]", "")
					.replaceAll("\\s", ",");
			if (!handler.paramIndexMapping.containsKey(entry.getKey())) {
				continue;
			}
			int index = handler.paramIndexMapping.get(entry.getKey());
			paramValues[index] = convert(paramTypes[index], value);
		}

		if (handler.paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
			int index = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
			paramValues[index] = req;
		}

		if (handler.paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
			int index = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
			paramValues[index] = resp;
		}

		Object returnValue = handler.method.invoke(handler.controller, paramValues);
		if (Objects.isNull(returnValue) || returnValue instanceof Void) {
			return;
		}
		resp.getWriter().write(returnValue.toString());
	}

	private Object convert(Class<?> type, String value) {
		if (Integer.class == type) {
			return Integer.valueOf(value);
		}

		// 如果还有其他的类型，如double等，可以继续处理
		return value;
	}

	private Handler getHandler(HttpServletRequest req) {
		if (handlerMapping.isEmpty()) {
			return null;
		}
		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String uri = requestURI.replace(contextPath, "").replaceAll("/+", "/");
		for (Handler handler : handlerMapping) {
			Matcher matcher = handler.pattern.matcher(uri);
			if (!matcher.matches()) {
				continue;
			}
			return handler;
		}
		return null;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {

		// 1. 加载配置文件
		doLoadConfig(config.getInitParameter("contextConfigLocation"));

		// 2. 扫描相关的类
		doScanner(contextConfig.getProperty("scanPackage"));

		// 3. 初始化相关的类，并放到IoC容器中
		doInstance();

		// 4. 完成依赖注入
		doAutowired();

		// 5. 初始化HandlerMapping
		initHandlerMapping();

		System.out.println("Manual MVC Framework is init");
	}

	private void initHandlerMapping() {
		if (ioc.isEmpty()) {
			return;
		}

		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			Class<?> clazz = entry.getValue().getClass();
			if (!clazz.isAnnotationPresent(Controller.class)) {
				continue;
			}

			String baseUrl = "";
			if (clazz.isAnnotationPresent(RequestMapping.class)) {
				RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
				baseUrl = requestMapping.value();

				// 获取所有方法
				Method[] methods = clazz.getMethods();
				Method[] declaredMethods = clazz.getDeclaredMethods();
				Set<Method> allMethods = new HashSet<>();
				allMethods.addAll(Arrays.asList(declaredMethods));
				allMethods.addAll(Arrays.asList(methods));

				for (Method method : allMethods) {
					if (!method.isAnnotationPresent(RequestMapping.class)) {
						continue;
					}
					RequestMapping requestMappingMethod = method.getAnnotation(RequestMapping.class);
					String regex = ("/" + baseUrl + "/" + requestMappingMethod.value()).replaceAll("/+", "/");
					Pattern pattern = Pattern.compile(regex);
					handlerMapping.add(new Handler(pattern, entry.getValue(), method));
					System.out.printf("Mapped uri [%s] for method [%s] \r\n", regex, method.getName());
				}
			}
		}
	}

	private void doAutowired() {
		if (ioc.isEmpty()) {
			return;
		}

		for (Map.Entry<String, Object> entry : ioc.entrySet()) {
			// 获取所有的字段属性
			Class<?> clazz = entry.getValue().getClass();
			Field[] declaredFields = clazz.getDeclaredFields();
			Field[] fields = clazz.getFields();
			Set<Field> allFields = new HashSet<>();
			allFields.addAll(Arrays.asList(declaredFields));
			allFields.addAll(Arrays.asList(fields));

			for (Field field : allFields) {
				if (!field.isAnnotationPresent(Autowired.class)) {
					continue;
				}

				Autowired autowired = field.getAnnotation(Autowired.class);
				String beanName = autowired.value();
				if ("".equals(beanName)) {
					beanName = field.getType().getName();
				}

				field.setAccessible(true);

				try {
					field.set(entry.getValue(), ioc.get(beanName));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void doInstance() {
		if (classNames.isEmpty()) {
			return;
		}

		try {
			for (String className : classNames) {
				Class<?> clazz = Class.forName(className);
				if (clazz.isAnnotationPresent(Controller.class)) {
					Object instance = clazz.newInstance();
					String beanName = toLowerFirstCase(clazz.getSimpleName());
					ioc.put(beanName, instance);

				} else if (clazz.isAnnotationPresent(Service.class)) {
					Service service = clazz.getAnnotation(Service.class);
					String beanName = service.value();
					if ("".equals(beanName)) {
						beanName = toLowerFirstCase(clazz.getName());
					}
					Object instance = clazz.newInstance();
					ioc.put(beanName, instance);
					// 根据类型自动赋值，取巧方式
					for (Class<?> i : clazz.getInterfaces()) {
						if (ioc.containsKey(i.getName())) {
							throw new RuntimeException(i.getName() + " has already in container");
						}
						ioc.put(i.getName(), instance);
					}
				} else {
					continue;
				}
			}

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private String toLowerFirstCase(String simpleName) {
		char[] chars = simpleName.toCharArray();
		chars[0] = Character.toLowerCase(chars[0]);
		return String.valueOf(chars);
	}

	private void doLoadConfig(String contextConfigLocation) {
		try(InputStream is = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation)) {
			contextConfig.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
				classNames.add(className);
			}
		}
	}

	private class Handler {
		protected Object controller;
		protected Method method;
		protected Pattern pattern;
		protected Map<String, Integer> paramIndexMapping;

		protected Handler(Pattern pattern, Object controller, Method method) {
			this.method = method;
			this.controller = controller;
			this.pattern = pattern;
			paramIndexMapping = new HashMap<>();
			putParamIndexMapping(method);
		}

		private Class<?>[] getParamTypes() {
			return method.getParameterTypes();
		}

		private void putParamIndexMapping(Method method) {
			Annotation[][] annotations = method.getParameterAnnotations();
			for (int i = 0; i < annotations.length; i++) {
				for (Annotation annotation : annotations[i]) {
					if (annotation instanceof RequestParam) {
						String paraName = ((RequestParam) annotation).value();
						if (!"".equals(paraName)) {
							paramIndexMapping.put(paraName, i);
						}
					}
				}
			}

			// 提取方法中的request和response
			Class<?>[] parameterTypes = method.getParameterTypes();
			for (int i = 0; i < parameterTypes.length; i++) {
				Class<?> parameterType = parameterTypes[i];
				if (parameterType == HttpServletRequest.class
						|| parameterType == HttpServletResponse.class) {
					paramIndexMapping.put(parameterType.getName(), i);
				}
			}
		}
	}
}
