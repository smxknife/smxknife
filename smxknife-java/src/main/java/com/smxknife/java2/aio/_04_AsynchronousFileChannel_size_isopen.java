package com.smxknife.java2.aio;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author smxknife
 * 2020/11/3
 */
public class _04_AsynchronousFileChannel_size_isopen {

	@Test
	public void test() {
		Path path = Paths.get(this.getClass().getClassLoader().getResource("aio/_01_lock").getPath());
		try(AsynchronousFileChannel channel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE)) {
			System.out.println(channel.size());
			System.out.println(channel.isOpen());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
