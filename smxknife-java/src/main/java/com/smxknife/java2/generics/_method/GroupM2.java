package com.smxknife.java2.generics._method;

/**
 * @author smxknife
 * 2019-04-17
 */
public class GroupM2<T> {

	// 这个T与类上面的T不是一个类型
	public <T> T get(T t) {
		try {
			Object o = t.getClass().newInstance();
			System.out.println("instance : value = " + o.toString() + ", " + o.getClass().getCanonicalName());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(t.getClass().getCanonicalName());
		return t;
	}

	public T classGet(T t) {
		System.out.println(t.getClass().getCanonicalName());
		return t;
	}

	public static void main(String[] args) {
		GroupM2<String> m2 = new GroupM2<>();
//		m2.get(1);
//		m2.classGet(1); // 编译错误
		m2.classGet("1");

		m2.get("ssss");

		m2.get(new Group());
	}
}
