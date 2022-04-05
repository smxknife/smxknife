package com.smxknife.java.ex30;

/**
 * @author smxknife
 * 2019/9/8
 */
public class Animal {

	// TODO 注意重写equals的参数必须是Object类型，如果不为Object类型，那么这不叫覆盖，叫重写
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	// TODO 这种方式当参数没有使用Object，而是自定义的类型，而且没有加@Override注解，那么这将会带来不少麻烦
	public boolean equals(Animal animal) {
		return true;
	}
}
