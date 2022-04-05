package com.smxknife.java2.thread.phaser.demo02;

import java.util.concurrent.Phaser;

/**
 * @author smxknife
 * 2019/8/26
 */
public class _Run_register {
	public static void main(String[] args) {
		Phaser phaser = new Phaser(5);

		System.out.println("registeredParties: " + phaser.getRegisteredParties());

		phaser.register();
		System.out.println("registeredParties: " + phaser.getRegisteredParties());

		phaser.register();
		System.out.println("registeredParties: " + phaser.getRegisteredParties());

		phaser.register();
		System.out.println("registeredParties: " + phaser.getRegisteredParties());

		phaser.bulkRegister(10);
		System.out.println("registeredParties: " + phaser.getRegisteredParties());

		phaser.bulkRegister(2);
		System.out.println("registeredParties: " + phaser.getRegisteredParties());

	}
}
