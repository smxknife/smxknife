package com.smxknife.patterns.proxy.demo3;

import com.smxknife.patterns.proxy.Service;
import com.smxknife.patterns.proxy.Service1Impl;
import sun.misc.ProxyGenerator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Proxy;

/**
 * @author smxknife
 * 2019/12/27
 */
public class Main {
	public static void main(String[] args) {
		Service service = new Service1Impl();
		ProxyHandler proxyHandler = new ProxyHandler(service);
		Class<? extends Service> serviceClass = service.getClass();
		// Proxy.newProxyInstance的作用就是实例化一个$Proxy0
		// $Proxy0的构造函数需要一个InvocationHandler参数，具体的业务实现就是在自定义的ProxyHandler中
		// 在ProxyHandler中调用invoke方法
		Service proxyService = (Service) Proxy.newProxyInstance(serviceClass.getClassLoader(), serviceClass.getInterfaces(), proxyHandler);
		proxyService.doSomething();

		byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", new Class[]{Service.class});
		try(FileOutputStream fos = new FileOutputStream("./$Proxy0.class")) {
			fos.write(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
