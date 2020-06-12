package com.smxknife.patterns.proxy.demo4;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author smxknife
 * 2019/12/27
 */
public class JdkProxy {

	public static final String ln = "\r\n";

	public static Object newProxyInstance(JdkClassLoader classLoader, Class<?>[] interfaces, JdkInvocationHandler handler) {
		try {
			// 动态生成源代码.java文件
			String src = generateSrc(interfaces);

			// 文件输出到磁盘
			String path = JdkProxy.class.getResource("").getPath();
			System.out.println("path = " + path);
			File file = new File(path + "$Proxy0.java");
			FileWriter fw = new FileWriter(file);
			fw.write(src);
			fw.flush();
			fw.close();

			// 把生成的.java文件编译成.class文件
			JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
			Iterable<? extends JavaFileObject> iterable = fileManager.getJavaFileObjects(file);

			JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, iterable);
			task.call();
			fileManager.close();

			// 把编译的class加载到JVM中
			Class proxyClass = classLoader.findClass("$Proxy0");
			Constructor constructor = proxyClass.getConstructor(JdkInvocationHandler.class);
//			file.delete();

			return constructor.newInstance(handler);

		} catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String generateSrc(Class<?>[] interfaces) {
		StringBuffer sb = new StringBuffer();
		sb.append("package ").append(JdkProxy.class.getPackage().getName()).append(";").append(ln);
		for (Class<?> i : interfaces) {
			sb.append("import ").append(i.getPackage().getName()).append(".").append(i.getSimpleName()).append(";").append(ln);
		}
		sb.append("import java.lang.reflect.*;").append(ln);
		sb.append("public class $Proxy0 implements ");
		for (Class<?> i : interfaces) {
			sb.append(i.getName()).append(",");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" {").append(ln);
		sb.append("    JdkInvocationHandler h;").append(ln);
		sb.append("    public $Proxy0 (JdkInvocationHandler h) {").append(ln);
		sb.append("        this.h = h;").append(ln);
		sb.append("    }").append(ln);
		for (Class<?> i : interfaces) {
			Method[] methods = i.getMethods();
			for (int i1 = 0; i1 < methods.length; i1++) {
				Method method = methods[i1];

				Class<?>[] parameterTypes = method.getParameterTypes();

				StringBuffer paramNames = new StringBuffer();
				StringBuffer paramValues = new StringBuffer();
				StringBuffer paramClasses = new StringBuffer();

				for (Class<?> cls : parameterTypes) {
					String type = cls.getName();
					String paramName = toLowerFirstCase(cls.getSimpleName());
					paramNames.append(type).append(" ").append(paramName);
					paramValues.append(paramName);
					paramClasses.append(type).append(".class");

					if (i1 > 0 && i1 < parameterTypes.length - 1) {
						paramNames.append(", ");
						paramClasses.append(", ");
						paramValues.append(", ");
					}
				}

				sb.append("    public ").append(method.getReturnType().getName()).append(" ").append(method.getName()).append(" (")
						.append(paramNames.toString()).append(") {").append(ln);
				sb.append("        try {").append(ln);
				sb.append("            Method m = ").append(i.getName()).append(".class.getMethod(\"").append(method.getName()).append("\", new Class[] {")
						.append(paramClasses.toString()).append("});").append(ln);
				sb.append(hasReturnValue(method.getReturnType()) ? "            return " : "")
						.append(getCaseCode("            this.h.invoke(this, m, new Object[] {" + paramValues.toString() + "})", method.getReturnType()))
						.append(";").append(ln);
				sb.append("        } catch(Error _ex) {").append(ln);
				sb.append("        } catch(Throwable e) {").append(ln);
				sb.append("            throw new UndeclaredThrowableException(e);").append(ln);
				sb.append("        }").append(ln);
				sb.append(getReturnEmptyCode(method.getReturnType())).append(ln);
				sb.append("    }").append(ln);
			}
		}
		sb.append("}");
		return sb.toString();
	}

	private static String toLowerFirstCase(String src) {
		char[] chars = src.toCharArray();
		// 变小写
		chars[0] += 32;
		return String.valueOf(chars);
	}

	private static Map<Class, Class> mappings = new HashMap<>();
	static {
		mappings.put(int.class, Integer.class);
	}

	private static String getReturnEmptyCode(Class<?> returnClass) {
		if (mappings.containsKey(returnClass)) {
			return "return 0;";
		} else if (returnClass == void.class) {
			return "";
		} else {
			return "return null;";
		}
	}

	private static String getCaseCode(String code, Class<?> returnType) {
		if (mappings.containsKey(returnType)) {
			return "((" + mappings.get(returnType).getName() + ")" + code + ")." + returnType.getSimpleName() + "Value()";
		}
		return code;
	}

	private static boolean hasReturnValue(Class<?> returnType) {
		return returnType != void.class;
	}
}
