package com.smxknife.java3._01_runtimecreateobject;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author smxknife
 * 2019/12/24
 */
public class _03_Class_constructor_Demo {
	public static void main(String[] args) {
		try {
			Constructor<_0_CommObj> constructor = _0_CommObj.class.getConstructor();
			constructor.newInstance();
			System.out.println("会调用静态代码块和构造函数");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}
