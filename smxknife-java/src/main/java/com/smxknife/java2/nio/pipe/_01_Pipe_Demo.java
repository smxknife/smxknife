package com.smxknife.java2.nio.pipe;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.util.concurrent.TimeUnit;

/**
 * @author smxknife
 * 2020/10/22
 */
public class _01_Pipe_Demo {

	@Test
	public void test1() {
		try {
			Pipe pipe = Pipe.open();

			try(Pipe.SinkChannel sinkChannel = pipe.sink();
			    Pipe.SourceChannel sourceChannel = pipe.source();) {

				new Thread(() -> {
					try {
						TimeUnit.SECONDS.sleep(1);
						for (int i = 0; i < 5; i++) {
							sinkChannel.write(ByteBuffer.wrap(String.format("客户端A_%s ", i).getBytes()));
						}
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}, "thread-a").start();

				new Thread(() -> {
					try {
						TimeUnit.SECONDS.sleep(1);
						for (int i = 0; i < 5; i++) {
							sinkChannel.write(ByteBuffer.wrap(String.format("客户端B_%s ", i).getBytes()));
						}
					} catch (InterruptedException | IOException e) {
						e.printStackTrace();
					}
				}, "thread-b").start();

				TimeUnit.SECONDS.sleep(3);

				ByteBuffer byteBuffer = ByteBuffer.allocate(1000);
				int readLength = 0;
				while ((readLength = sourceChannel.read(byteBuffer))  != -1) {
					System.out.println("source接收到：" + new String(byteBuffer.array(), 0, readLength));
				}
			}

		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
