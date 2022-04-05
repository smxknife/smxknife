package com.smxknife.java.ex18;

/**
 * @author smxknife
 * 2018/11/16
 */
public class ParamDemo {

	public static void main(String[] args) {
		ParamDemo paramDemo = new ParamDemo();
		Param param = null;
		System.out.println(param);
		paramDemo.change(param);
		System.out.println(param);

		Param param1 = new Param();
		param1.setAge(2);
		paramDemo.change(param1);
		System.out.println(param1);

	}

	private void change(Param param) {
		param = new Param();
		param.setAge(1);
	}
}

class Param {
	private int age;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Param{" +
				"age=" + age +
				'}';
	}
}
