package com.smxknife.java2.invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author smxknife
 * 2021/6/28
 */
public class _01_MethodHandler {
	public static void main(String[] args) {
		/**
		 * MethodType，的关注点其实是返回值类型和参数类型
		 * 这里针对的是HelloService的sayHi方法
		 * 参数有两个，string和int
		 * 返回值是string
		 */
		final MethodType methodType = MethodType.methodType(String.class, String.class, int.class);

		/**
		 * MethodHandle.Lookup相当于MethodHandle工厂类，通过findxxx方法可以得到相应的MethodHandle，
		 * 还可以配合反射API创建MethodHandle，对应的方法有unreflect、unreflectSpecial等
		 */
		final MethodHandles.Lookup lookup = MethodHandles.lookup();

		try {
			final MethodHandle sayHiMethodHandler = lookup.findVirtual(HelloService.class, "sayHi", methodType);

			HelloService helloService = new HelloService();

			// 使用方法1
			final Object invoke = sayHiMethodHandler.invoke(helloService, "smxknife", 1);
			System.out.println(invoke);

			// 使用方法2
			final MethodHandle methodHandle = sayHiMethodHandler.bindTo(helloService);
			final Object smxknife2 = methodHandle.invokeWithArguments("smxknife2", 2);
			System.out.println(smxknife2);

			// 访问私有方法试试
			final MethodType sayHelloMethodType = MethodType.methodType(String.class);
			final MethodHandle sayHello = lookup.findSpecial(HelloService.class, "sayHello", sayHelloMethodType, _01_MethodHandler.class);

			final Object helloResult = sayHello.invoke(helloService);
			System.out.println(helloResult);


		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}


	}
}
