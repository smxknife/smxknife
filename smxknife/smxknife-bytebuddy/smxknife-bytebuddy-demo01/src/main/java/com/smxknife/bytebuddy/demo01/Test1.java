package com.smxknife.bytebuddy.demo01;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author smxknife
 * 2020/10/20
 */
public class Test1 {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException {
		// 创建了类，但是还没有加载到jvm中
		// 这个新的类的表现形式是DynamicType.Unloaded的一个实例，具体地说是DynamicType.Unloaded中包含了新的类的字节码。
		DynamicType.Unloaded unloaded = new ByteBuddy()
				.subclass(Object.class)
				.method(ElementMatchers.isToString())
				.intercept(FixedValue.value("Hello, world, bytebuddy"))
				.make();

		// 在使用生成的类之前我们先要把它加载到JVM中
		DynamicType.Loaded loaded = unloaded.load(Test1.class.getClassLoader());

		// 实例化loadedClass代表的class类型，然后调用这个实例的toString()方法
		Class loadedClass = loaded.getLoaded();
		Object o = loadedClass.newInstance();
		System.out.println(o);

	}
}
