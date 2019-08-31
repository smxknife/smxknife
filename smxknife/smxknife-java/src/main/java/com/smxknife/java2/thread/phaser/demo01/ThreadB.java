package com.smxknife.java2.thread.phaser.demo01;

import lombok.AllArgsConstructor;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2019/8/26
 */
@AllArgsConstructor
public class ThreadB extends Thread {

	private Phaser phaser;

	@Override
	public void run() {
		PrintTools.methodA();
	}
}
