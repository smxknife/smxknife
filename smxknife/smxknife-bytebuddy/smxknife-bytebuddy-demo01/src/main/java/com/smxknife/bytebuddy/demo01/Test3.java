package com.smxknife.bytebuddy.demo01;

import com.smxknife.bytebuddy.common.Bar2;
import com.smxknife.bytebuddy.common.Foo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author smxknife
 * 2020/10/20
 */
public class Test3 {
	public static void main(String[] args) throws IllegalAccessException, InstantiationException {

		String sayHello = new ByteBuddy()
				.subclass(Foo.class)
				.method(ElementMatchers.named("sayHelloFoo")
						.and(ElementMatchers.isDeclaredBy(Foo.class))
						.and(ElementMatchers.returns(String.class)))
				.intercept(MethodDelegation.to(Bar2.class)) // 从这里以委托的方式
				.make()
				.load(Test3.class.getClassLoader())
				.getLoaded()
				.newInstance()
				.sayHelloFoo();

		// TODO 需要注意的是，虽然Foo和Bar的方法名不一样，但是ByteBuddy会根据 方法签名、返回值类型、方法名、注解顺序来匹配
		// TODO 所以，虽然sayHelloFoo和sayHelloBar的方法名不一样，但是他们拥有相同的方法签名和返回值类型
		// TODO 这里注意的是sayHelloBar是static方法

		System.out.println(sayHello);

	}
}
