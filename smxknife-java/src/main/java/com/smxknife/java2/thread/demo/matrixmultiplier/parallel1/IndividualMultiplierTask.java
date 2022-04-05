package com.smxknife.java2.thread.demo.matrixmultiplier.parallel1;

/**
 * @author smxknife
 * 2019-08-02
 */
public class IndividualMultiplierTask implements Runnable {

	private final double[][] result;
	private final double[][] matrixA;
	private final double[][] matrixB;

	private final int row;
	private final int column;

	public IndividualMultiplierTask(double[][] matrixA, double[][] matrixB, double[][] result, int row, int column) {
		this.matrixA = matrixA;
		this.matrixB = matrixB;
		this.result = result;
		this.row = row;
		this.column = column;
	}

	@Override
	public void run() {
		result[row][column] = 0;
		for (int i = 0; i < matrixA[row].length; i++) {
			result[row][column] += matrixA[row][i] * matrixB[i][column];
		}
	}
}
