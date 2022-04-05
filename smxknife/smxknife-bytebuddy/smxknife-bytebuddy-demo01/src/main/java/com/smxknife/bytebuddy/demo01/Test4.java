package com.smxknife.bytebuddy.demo01;

import com.smxknife.bytebuddy.common.Bar;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author smxknife
 * 2020/10/20
 */
public class Test4 {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {

		Class<?> loadedClass = new ByteBuddy()
				.subclass(Object.class)
				.name("MyClassName")
				.defineMethod("custom", String.class, Modifier.PUBLIC)
				.intercept(MethodDelegation.to(Bar.class))
				.defineField("x", String.class, Modifier.PUBLIC)
				.make()
				.load(Test4.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
				.getLoaded();

		System.out.println(loadedClass.getName());
		Method custom = loadedClass.getDeclaredMethod("custom");
		Object invoke = custom.invoke(loadedClass.newInstance());
		String sayHelloBar = Bar.sayHelloBar();
		System.out.println(invoke + ", hashCode = " + invoke.hashCode());
		System.out.println(sayHelloBar + ", hashCode = " + sayHelloBar.hashCode());
		Field declaredField = loadedClass.getDeclaredField("x");
		System.out.println(declaredField);
		System.out.println(declaredField.get(loadedClass.newInstance()));
	}
}
