package com.smxknife.java2.generics.edge;

import java.util.Objects;

/**
 * @author smxknife
 * 2020/11/5
 */
public class ExtendDemo {

	public static void main(String[] args) {

		AWrapper_Out();
		AWrapper_Get();

		AExtendWrapper_Out();

	}

	private static void AExtendWrapper_Out() {
		AExtendWrapper<A> aaWrapper_A = new AExtendWrapper<>();
		aaWrapper_A.out(new A());
		aaWrapper_A.out(new SubA());
		aaWrapper_A.out(new LittleA());

		AExtendWrapper<SubA> aaWrapper_SubA = new AExtendWrapper<>();
		// aaWrapper_SubA.out(new A()); // 编译报错
		aaWrapper_SubA.out(new SubA());
		aaWrapper_SubA.out(new LittleA());

		AExtendWrapper<? extends A> aWrapperExtendsA = new AExtendWrapper<>();
		// aWrapperExtendsA.out(new A()); // 编译报错
		// aWrapperExtendsA.out(new SubA()); // 编译报错
		// aWrapperExtendsA.out(new LittleA()); // 编译报错
		// aWrapperExtendsA.out(new Object()); // 编译报错
		// aWrapperExtendsA.out(new S()); // 同样报错
		aWrapperExtendsA.out(null);

		AExtendWrapper<? super A> aWrapperSuperA = new AExtendWrapper<>();
		aWrapperSuperA.out(new A());
		aWrapperSuperA.out(new SubA());
		aWrapperSuperA.out(new LittleA());
	}

	private static void AWrapper_Get() {
		AWrapper<A> aaWrapper_A = new AWrapper<>();
		A a = aaWrapper_A.get();
		A a1 = aaWrapper_A.get();
		// SubA a1 = aaWrapper_A.get(new SubA()); // 编译报错
		A a2 = aaWrapper_A.get();
		// LittleA a2 = aaWrapper_A.get(new LittleA()); // 编译报错

		AWrapper<SubA> aaWrapper_SubA = new AWrapper<>();
		A aa = aaWrapper_SubA.get(); // 说明可以是父类
		A subA = aaWrapper_SubA.get();
		// LittleA subA1 = aaWrapper_SubA.get(); // 编译报错，说明不可以是子类

		AWrapper<? extends A> aWrapperExtendsA = new AWrapper<>();
		S sss = aWrapperExtendsA.get(); // 说明父类是可以
		A aaa = aWrapperExtendsA.get();
		// SubA subA1 = aWrapperExtendsA.get(); // 编译报错，说明子类不行

		AWrapper<? super A> aWrapperSuperA = new AWrapper<>();
//		S ssss = aWrapperSuperA.get(); // 编译报错
//		A aaaa = aWrapperSuperA.get(); // 编译报错
//		SubA subA2 = aWrapperSuperA.get(); // 编译报错
	}

	private static void AWrapper_Out() {
		AWrapper<A> aaWrapper_A = new AWrapper<>();
		aaWrapper_A.out(new A());
		aaWrapper_A.out(new SubA());
		aaWrapper_A.out(new LittleA());

		AWrapper<SubA> aaWrapper_SubA = new AWrapper<>();
		// aaWrapper_SubA.out(new A()); // 编译报错
		aaWrapper_SubA.out(new SubA());
		aaWrapper_SubA.out(new LittleA());

		AWrapper<? extends A> aWrapperExtendsA = new AWrapper<>();
		// aWrapperExtendsA.out(new A()); // 编译报错
		// aWrapperExtendsA.out(new SubA()); // 编译报错
		// aWrapperExtendsA.out(new LittleA()); // 编译报错
		// aWrapperExtendsA.out(new Object()); // 编译报错
		// aWrapperExtendsA.out(new S()); // 同样报错
		aWrapperExtendsA.out(null);

		AWrapper<? super A> aWrapperSuperA = new AWrapper<>();
		aWrapperSuperA.out(new A());
		aWrapperSuperA.out(new SubA());
		aWrapperSuperA.out(new LittleA());
	}
}

class S {}

class A extends S {}

class SubA extends A {}

class LittleA extends SubA {}

class AWrapper<T> {
	void out(T t) {
		System.out.println(Objects.isNull(t) ? null : t.getClass().getName());
	}

	T get() {
		return null;
	}
}

class AExtendWrapper<T extends A> {
	void out(T t) {
		System.out.println(Objects.isNull(t) ? null : t.getClass().getName());
	}

	T get() {
		return null;
	}
}
