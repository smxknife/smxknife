package com.smxknife.java2.io.input;

import lombok.Getter;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Random;

/**
 * @author smxknife
 * 2019-02-13
 */
public class PipedInputStreamDemo2 {
	public static void main(String[] args) throws IOException {
		SenderDemo2 sender = new SenderDemo2();
		ReceiverDemo2 receiver = new ReceiverDemo2();
		sender.getPos().connect(receiver.getPis());

		new Thread(sender).start();
		new Thread(receiver).start();
	}
}

class ReceiverDemo2 implements Runnable {

	@Getter
	private PipedInputStream pis = new PipedInputStream();

	@Override
	public void run() {
		receive();
	}

	private void receive() {
		try {
			byte[] bytes = new byte[4];
			System.out.println("prepare reading...");
			int res = 0;
			while ((res = pis.read(bytes)) != -1) {
				for (int i = 0; i < bytes.length; i++) {
					System.out.printf("receive %s \r\n", bytes[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class SenderDemo2 implements Runnable {

	private Random random = new Random();

	@Getter
	private PipedOutputStream pos = new PipedOutputStream();

	@Override
	public void run() {
		send();
	}

	private void send() {
		while (true) {
			int[] objects = random.ints(1, 256).limit(4).toArray();
			System.out.println(objects.length);
			byte[] bytes = new byte[objects.length];
			for (int i = 0; i < objects.length; i++) {
				bytes[i] = (byte) objects[i];
			}
			for (int i = 0; i < bytes.length; i++) {
				System.out.println(bytes[i]);
			}
			try {
				pos.write(bytes);
				pos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
