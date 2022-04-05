package com.smxknife.java2.thread.demo.matrixmultiplier.parallel1;

import java.util.ArrayList;
import java.util.List;

/**
 * @author smxknife
 * 2019-08-02
 */
public class ParallelIndividualMultiplier {

	public static void multiply(double[][] matrixA, double[][] matrixB, double[][] result) {
		List<Thread> threads = new ArrayList<>();
		int rowA = matrixA.length;
		int columnB = matrixB[0].length;

		for (int i = 0; i < rowA; i++) {
			for (int j = 0; j < columnB; j++) {
				IndividualMultiplierTask task = new IndividualMultiplierTask(matrixA, matrixB, result, i, j);
				Thread thread = new Thread(task, "thread-row:" + i + "|column:" + j);
				thread.start();
				threads.add(thread);

				if (threads.size() % 10 == 0) {
					waiteForThreads(threads);
				}
			}
		}
	}

	private static void waiteForThreads(List<Thread> threads) {
		threads.forEach(thread -> {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		threads.clear();
	}
}
