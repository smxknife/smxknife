package com.smxknife.java2.classloader.serviceloader;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author smxknife
 * 2019/11/6
 */
public class _Run {
	public static void main(String[] args) {
		System.out.println(_Run.class.getClassLoader());
		System.out.println(_Run.class.getClassLoader().getParent());
		System.out.println(_Run.class.getClassLoader().getParent().getParent());

		ServiceLoader<Driver> driversLoader = ServiceLoader.load(Driver.class);
		Iterator<Driver> iterator = driversLoader.iterator();
		while (iterator.hasNext()) {
			Driver driver = iterator.next();
			System.out.println(driver.name());
			System.out.println(driver.getClass().getClassLoader());
			System.out.println(driver.getClass().getClassLoader().getParent());
			System.out.println(driver.getClass().getClassLoader().getParent().getParent());
		}
	}
}
