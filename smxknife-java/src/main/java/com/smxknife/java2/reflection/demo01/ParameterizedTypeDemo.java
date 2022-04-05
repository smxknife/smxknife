package com.smxknife.java2.reflection.demo01;

import java.lang.reflect.Method;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;

/**
 * @author smxknife
 * 2020/11/22
 */
public class ParameterizedTypeDemo {
	public static void main(String[] args) {
		Arrays.asList(_CommonObj.class.getDeclaredMethods()).forEach(m -> {
			System.out.println("method name: " + m.getName());
			Arrays.asList(m.getParameters()).forEach(p -> {
				System.out.println("-- parameter getName | " + p.getName());
				System.out.println("-- parameter getParameterizedType() | " + p.getParameterizedType());
				System.out.println("-- parameter getParameterizedType().getTypeName() | " + p.getParameterizedType().getTypeName());
			});
			TypeVariable<Method>[] typeParameters = m.getTypeParameters();
			Arrays.asList(typeParameters).forEach(tv -> {
				System.out.println("-- typeVariable.getName | " + tv.getName());
			});
			System.out.println("---------");
		});
	}
}
