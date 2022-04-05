package com.smxknife.mapstruct.demo02;

/**
 * @author smxknife
 * 2021/3/8
 */
public class Demo02 {
	public static void main(String[] args) {
		Dto02 dto02 = new Dto02();
		dto02.setId(1);
		dto02.setName("hello");
		dto02.setValue(10.0);
		Entity02 entity02 = Mapper02.INSTANCE.toEntity(dto02);
		System.out.println(entity02);
	}
}
