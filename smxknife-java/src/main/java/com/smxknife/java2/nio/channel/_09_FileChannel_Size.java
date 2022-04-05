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
public class _09_FileChannel_Size {
	public static void main(String[] args) {

		// 返回此通道关联文件的当前大小
		// sizeTest1();

		// channel position可以设置位置超过文件当前的大小，但是不会修改文件大小，如果读取返回-1，如果写入将扩容文件大小；在之前文件末尾和新指定位置之间的字节是未指定的
		sizeTest2();

	}

	private static void sizeTest2() {
		try(FileOutputStream fos = new FileOutputStream(new File(_09_FileChannel_Size.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer1 = ByteBuffer.wrap("abcde".getBytes());

			System.out.println("A fileChannel position = " + channel.position() + "，file size = " + channel.size());

			channel.position(10);

			try {
				ByteBuffer readInByteBuffer = ByteBuffer.allocate(10);
				int readLength = channel.read(readInByteBuffer);
				System.out.println("readLength = " + readLength);
			} catch (Exception e) {
				e.printStackTrace();
			}


			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer1));
			System.out.println("B fileChannel position = " + channel.position() + "，file size = " + channel.size());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void sizeTest1() {
		try(FileOutputStream fos = new FileOutputStream(new File(_09_FileChannel_Size.class.getClassLoader().getResource("channel/_01_FileChannel_write").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("abcde".getBytes());
			System.out.println("A fileChannel position = " + channel.position() + ", file size = " + channel.size());
			System.out.println("Write 1 返回值 ： " + channel.write(byteBuffer));
			System.out.println("B fileChannel position = " + channel.position() + ", file size = " + channel.size());

			channel.position(3);
			System.out.println("C fileChannel position = " + channel.position() + ", file size = " + channel.size());
			byteBuffer.rewind();
			channel.write(byteBuffer);
			System.out.println("D fileChannel position = " + channel.position() + ", file size = " + channel.size());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
