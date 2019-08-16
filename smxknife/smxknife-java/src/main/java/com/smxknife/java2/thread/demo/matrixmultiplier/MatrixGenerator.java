package com.smxknife.java2.thread.demo.matrixmultiplier;

import java.util.Random;

/**
 * @author smxknife
 * 2019-08-02
 */
public class MatrixGenerator {

	public static double[][] generate(int row, int column) {
		double[][] ret = new double[row][column];
		Random random = new Random();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				ret[i][j] = random.nextFloat() * 10;
			}
		}
		return ret;
	}
}
