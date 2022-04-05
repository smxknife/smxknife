package com.smxknife.java2.beans;

import java.beans.BeanDescriptor;
import java.util.Enumeration;

/**
 * @author smxknife
 * 2018/11/9
 */
public class BeanDescriptorDemo {
	public static void main(String[] args) {
		BeanDescriptor descriptor = new BeanDescriptor(MyBean.class);
		System.out.println(descriptor.getBeanClass());
		System.out.println(descriptor.getCustomizerClass());
		System.out.println(descriptor.getDisplayName());
		System.out.println(descriptor.getName());
		System.out.println(descriptor.getShortDescription());
		Enumeration<String> stringEnumeration = descriptor.attributeNames();
		while (stringEnumeration.hasMoreElements()) {
			System.out.println(stringEnumeration.nextElement());
		}
		System.out.println("============");
		BeanDescriptor descriptor1 = new BeanDescriptor(MyInter.class, MyInterImpl.class);
		System.out.println(descriptor1.getBeanClass());
		System.out.println(descriptor1.getCustomizerClass());
		System.out.println(descriptor1.getDisplayName());
		System.out.println(descriptor1.getName());
		System.out.println(descriptor1.getShortDescription());
		Enumeration<String> stringEnumeration1 = descriptor1.attributeNames();
		while (stringEnumeration1.hasMoreElements()) {
			System.out.println(stringEnumeration1.nextElement());
		}
	}
}
