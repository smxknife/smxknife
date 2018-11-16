package com.smxknife.java2.io.input;

import lombok.Getter;
import lombok.extern.java.Log;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * @author smxknife
 * 2018/11/14
 */
public class PipedInputStreamDemo {
	public static void main(String[] args) throws IOException {
		Receiver receiver = new Receiver();
		Sender sender = new Sender();

		PipedInputStream pipedInputStream = receiver.getPipedInputStream();
		PipedOutputStream pipedOutputStream = sender.getPipedOutputStream();

		pipedInputStream.connect(pipedOutputStream);

		new Thread(sender).start();
		new Thread(receiver).start();
	}
}

@Log
class Receiver implements Runnable {

	@Getter
	private PipedInputStream pipedInputStream = new PipedInputStream();

	@Override
	public void run() {
		try {
//			readMessageOnce();
			readMessageContinued();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readMessageContinued() throws IOException {
		System.out.println("read continued");
		byte[] bytes = new byte[2048];
		int pos = 0;
		while ((pos = pipedInputStream.read(bytes)) != -1) {
			System.out.println(bytes.length);
			System.out.println("read continued finished");
			System.out.println(new String(bytes, 0, pos));
		}
		pipedInputStream.close();
	}

	private void readMessageOnce() throws IOException {
		byte[] bytes = new byte[2048];
		System.out.println("read ");
		System.out.println(pipedInputStream.available());
		int read = pipedInputStream.read(bytes);
		System.out.println("read finished");
		System.out.println(read);
		System.out.println(new String(bytes, 0, read));
		pipedInputStream.close();
	}
}

@Log
class Sender implements Runnable {

	@Getter
	private PipedOutputStream pipedOutputStream = new PipedOutputStream();

	@Override
	public void run() {
		try {
//			writeShortMessage();
			writeLongMessage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeLongMessage() throws IOException {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 102; i++) {
			builder.append("你好12345678");
		}
		pipedOutputStream.write(builder.toString().getBytes());
		pipedOutputStream.close();
	}

	private void writeShortMessage() throws IOException {
		String msg = "hello, 你好";
		pipedOutputStream.write(msg.getBytes());
		pipedOutputStream.close();
	}
}
