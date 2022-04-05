package com.smxknife.java2.thread.demo.matrixmultiplier.serial;

/**
 * @author smxknife
 * 2019-08-02
 */
public class SerialMultiplier {

	public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
		int rowA = matrixA.length;
		int columnA = matrixA[0].length;

		int columnB = matrixB[0].length;

		double[][] result = new double[rowA][columnB];

		for (int i = 0; i < rowA; i++) {
			for (int j = 0; j < columnB; j++) {
				result[i][j] = 0;
				for (int k = 0; k < columnA; k++) {
					result[i][j] += matrixA[i][k] * matrixB[k][j];
				}
			}
		}
		return result;
	}
}
