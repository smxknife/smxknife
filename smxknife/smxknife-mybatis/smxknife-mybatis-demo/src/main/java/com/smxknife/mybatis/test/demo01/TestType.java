package com.smxknife.mybatis.test.demo01;

import org.apache.ibatis.reflection.TypeParameterResolver;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/**
 * @author smxknife
 * 2020/11/22
 */
public class TestType {
	static SubClassA<Long> sa = new SubClassA<>();
	public static void main(String[] args) throws NoSuchFieldException {
		Field field = ClassA.class.getDeclaredField("map");
		// 获取字段的声明类型
		Type fieldGenericType = field.getGenericType();
		System.out.println("map Field getGenericType | " + fieldGenericType);
		System.out.println(fieldGenericType instanceof ParameterizedType);
		System.out.println("fieldGenericType instanceof ParameterizedType 返回true，" +
				"说明field的声明类型是一个参数化类型，可以简单理解为泛型类型" +
				"，对比下面的integer字段的输出为false，可以更加清楚的说明这一点");

		// 获取字段声明类型
		Field integerField = ClassA.class.getDeclaredField("integer");
		Type integerFieldGenericType = integerField.getGenericType();
		System.out.println("integer Field getGenericType | " + integerFieldGenericType);
		System.out.println("isParameterizedType | " + (integerFieldGenericType instanceof ParameterizedType));

		System.out.println();
		Arrays.asList(((ParameterizedType) fieldGenericType).getActualTypeArguments()).forEach(t -> {
			System.out.println(t);
		});
		System.out.println(((ParameterizedType)fieldGenericType).getOwnerType());
		System.out.println(((ParameterizedType)fieldGenericType).getRawType());

		Type type = TypeParameterResolver.resolveFieldType(field,
				ParameterizedTypeImpl.make(SubClassA.class, new Type[]{Long.class}, TestType.class));
		System.out.println(type.getClass());

		ParameterizedType parameterizedType = (ParameterizedType) type;
		System.out.println(parameterizedType.getRawType());
		System.out.println(parameterizedType.getOwnerType());
		Arrays.asList(parameterizedType.getActualTypeArguments()).forEach(t -> {
			System.out.println(t);
		});
	}
}
