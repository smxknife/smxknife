package com.smxknife.java2.jvm.error;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 通过不断的创建类，模拟方法区内存溢出，为了更加快速模拟，需要通过设置-XX:MaxMetaspaceSize=1M
 * java -cp . -XX:MaxMetaspaceSize=1M com.smxknife.java2.jvm.error.JavaMethodOutOfMemoryError
 * @author smxknife
 * 2019-06-16
 */
public class JavaMethodOutOfMemoryError {
	public static void main(String[] args) {
		int num = 0;
		while (true) {
			createClass(num++);
		}
	}

	private static void createClass(int num) {
		System.out.println("current num = " + num);
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(OOMObject.class);
		enhancer.setUseCache(false);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
				return methodProxy.invokeSuper(o, objects);
			}
		});
		enhancer.create();
	}


}


