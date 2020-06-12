package com.smxknife.patterns.singleton.demo02;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author smxknife
 * 2019/12/26
 */
public class LazyInitializationWithReflectDemo {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		// 这里还需要注意的一点是getConstructor是访问不到private
//		Constructor<LazyInitialization> constructor = LazyInitialization.class.getConstructor();
		Constructor<Mode_LazyInitialization> constructor = Mode_LazyInitialization.class.getDeclaredConstructor();
		constructor.setAccessible(true);

		Mode_LazyInitialization instance1 = constructor.newInstance();
		Mode_LazyInitialization instance2 = constructor.newInstance();

		System.out.println(instance1);
		System.out.println(instance2);
		System.out.println(instance1 == instance2);
		System.out.println("从输出结果来看，通过反射是可以破坏单例的");

		System.out.println("-----------------------------------------");
		Constructor<Mode_LazyInitializationCanPreventReflect> preventReflectConstructor = Mode_LazyInitializationCanPreventReflect.class.getDeclaredConstructor();
		preventReflectConstructor.setAccessible(true);
		try {
			Mode_LazyInitializationCanPreventReflect obj1 = preventReflectConstructor.newInstance();
			System.out.println("obj1 new | " + obj1);
			Mode_LazyInitializationCanPreventReflect obj2 = preventReflectConstructor.newInstance();
			System.out.println("obj2 new | " + obj2);
		} catch (InstantiationException|InvocationTargetException|RuntimeException e) {
			System.out.println(e.getLocalizedMessage());
		}
		System.out.println("从这个输出结果来看，通过newInstance是无法进行创建对象的");
		System.out.println("-----------------------------------------");

		System.out.println("上面这个试验发现反射是无法破坏单例了，但是动态创建对象的方式不止一种，下面采用Unsafe的方式");
		Object o1 = UnsafeWrapper.unsafe().allocateInstance(Mode_LazyInitializationCanPreventReflect.class);
		Object o2 = UnsafeWrapper.unsafe().allocateInstance(Mode_LazyInitializationCanPreventReflect.class);
		System.out.println("unsafe 1：" + o1);
		System.out.println("unsafe 2：" + o2);
		System.out.println(o1 == o2);
		System.out.println("从这个结果来看，果然，Unsafe是可以绕过构造函数验证，直接创建对象，所以这种方式依然存在缺陷");
		System.out.println("-----------------------------------------");

		Object p1 = UnsafeWrapper.unsafe().allocateInstance(Mode_LazyInitializationCanPreventReflect2.class);
		Object p2 = UnsafeWrapper.unsafe().allocateInstance(Mode_LazyInitializationCanPreventReflect2.class);
		System.out.println("unsafe p1：" + p1);
		System.out.println("unsafe p2：" + p2);
		System.out.println(p1 == p2);
		System.out.println("上面这个方法经过改进，可以防止初始化多个，但是既然引入了静态语句块，那么又与饿汉式有什么区别！！！");


	}
}
