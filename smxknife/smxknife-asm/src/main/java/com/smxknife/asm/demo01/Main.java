package com.smxknife.asm.demo01;

import org.objectweb.asm.ClassReader;

import java.io.IOException;

/**
 * @author smxknife
 * 2018/11/9
 */
public class Main {
	public static void main(String[] args) throws IOException {
		ClassPrinter printer = new ClassPrinter();
		ClassReader reader = new ClassReader("java.lang.Runnable");
		reader.accept(printer, 0);
		System.out.println(reader.getAccess());
		System.out.println(reader.getClassName());
		System.out.println(reader.getClass());
		System.out.println(reader.getInterfaces());
		System.out.println("-------------------------");
		for (int i = 0; i < reader.getItemCount(); i++) {
			System.out.println(reader.getItem(i));
		}
		System.out.println("-------------------------");
		System.out.println(reader.getMaxStringLength());
		System.out.println(reader.getSuperName());
	}
}
