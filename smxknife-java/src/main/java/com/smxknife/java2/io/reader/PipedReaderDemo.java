package com.smxknife.java2.io.reader;

import lombok.Getter;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * @author smxknife
 * 2018/11/19
 */
public class PipedReaderDemo {
	public static void main(String[] args) throws IOException {
		Sender sender = new Sender();
		Receiver receiver = new Receiver();
		sender.getPipedWriter().connect(receiver.getPipedReader());

		new Thread(sender).start();
		new Thread(receiver).start();
	}
}

class Sender implements Runnable {

	@Getter
	private PipedWriter pipedWriter = new PipedWriter();

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			writeMsg(i);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			pipedWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeMsg(int i) {
		try {
			System.out.println("------------");
			pipedWriter.write("Hello_" + i);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Receiver implements Runnable {

	@Getter
	private PipedReader pipedReader = new PipedReader();

	private boolean stop = false;

	@Override
	public void run() {
		while (!stop) {
			receiveMsg();
		}
	}

	private void receiveMsg() {
		char[] chars = new char[1024];
		try {
			int read = pipedReader.read(chars);
			if (read == -1) {stop = true;}
			System.out.println("receive: " + new String(chars));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
