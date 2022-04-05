package com.smxknife.java2.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _10_FileChannel_Truncate {
	public static void main(String[] args) {

		// 将此通道文件截取为给定大小（len）
		/**
		 * 1. 如果len < file.length，截取文件，丢弃len后面的所有字节
		 * 2. 如果len >= file.length，不修改文件
		 * 无论上面那种情况，只要通道的位置大于len，则将位置设置为该大小
		 */
		// truncateTest1();

		// position 大于当前文件大小
		// truncateTest2();


	}

	private static void truncateTest2() {
		try(FileOutputStream fos = new FileOutputStream(new File(_10_FileChannel_Truncate.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("12345678".getBytes());
			System.out.println("A fileChannel position = " + channel.position() + ", file size = " + channel.size());
			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer));
			System.out.println("B fileChannel position = " + channel.position() + ", file size = " + channel.size());

			channel.position(4);
			System.out.println("C fileChannel position = " + channel.position() + ", file size = " + channel.size());
			channel.truncate(1000);
			System.out.println("D fileChannel position = " + channel.position() + ", file size = " + channel.size());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void truncateTest1() {
		try(FileOutputStream fos = new FileOutputStream(new File(_10_FileChannel_Truncate.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("12345678".getBytes());
			System.out.println("A fileChannel position = " + channel.position() + ", file size = " + channel.size());
			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer));
			System.out.println("B fileChannel position = " + channel.position() + ", file size = " + channel.size());

			channel.position(8);
			System.out.println("C fileChannel position = " + channel.position() + ", file size = " + channel.size());
			channel.truncate(4);
			System.out.println("D fileChannel position = " + channel.position() + ", file size = " + channel.size());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
