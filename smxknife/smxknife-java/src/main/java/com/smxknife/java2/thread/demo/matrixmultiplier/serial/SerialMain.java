package com.smxknife.java2.thread.demo.matrixmultiplier.serial;

import com.smxknife.java2.thread.demo.matrixmultiplier.MatrixGenerator;

/**
 * @author smxknife
 * 2019-08-02
 */
public class SerialMain {
	public static void main(String[] args) {
		double[][] matrixA = MatrixGenerator.generate(2000, 2000);
		double[][] matrixB = MatrixGenerator.generate(2000, 2000);

		System.out.println("init finished!");
		long start = System.currentTimeMillis();
		double[][] result = SerialMultiplier.multiply(matrixA, matrixB);
		System.out.printf("Serial: %d%n", (System.currentTimeMillis() - start) / 1000);
	}
}
