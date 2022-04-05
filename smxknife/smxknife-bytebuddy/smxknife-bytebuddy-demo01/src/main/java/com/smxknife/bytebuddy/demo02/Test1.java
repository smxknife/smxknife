package com.smxknife.bytebuddy.demo02;

import com.smxknife.bytebuddy.common.Foo;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @author smxknife
 * 2020/10/20
 */
public class Test1 {
	public static void main(String[] args) {
		Foo orgFoo = new Foo();
		System.out.println(orgFoo.sayHelloFoo());
		ByteBuddyAgent.install();
		Class<? extends Foo> redefinedClass = new ByteBuddy()
				.redefine(Foo.class)
				.method(ElementMatchers.named("sayHelloFoo"))
				.intercept(FixedValue.value("Hello Foo ReDefined"))
				.make()
				.load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent())
				.getLoaded();

		Foo foo = new Foo();
		System.out.println(foo.sayHelloFoo());
	}
}
