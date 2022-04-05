package com.smxknife.java2.nio.channel;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _11_FileChannel_TransferTo {
	public static void main(String[] args) {

		// 给定位置大于该文件当前大小，则不传输任何字节
		// transferToTest1();

		// 正常传输数据（注：因为这里采用的是加载类空间的文件，也就是说是target的classes目录下的，这里涉及到maven的compile和clean问题，可能会导致死锁）
		// 返回结果是移动的字节数
		// transferToTest2();

		// 验证如果count的字节个数大于position到size的字节个数，则传输通道的size - position个字节数到dest通道的当前位置
		// 返回的结果是实际移动的字节数
		// transferToTest3();

		// 验证如果count的字节个数小于或等于position到size的字节个数，则传输count个字节到dest通道当前位置
		// srcFileChannel position不会更改
		// dstFileChannel position会更改
		// transferToTest4();

		// 另外还有一点需要注意，transferTo最大只能传输2G的数据，这个是在源码中写死的处理，这里不做验证
		/**
		 * int var8 = (int)Math.min(var3, 2147483647L); // 源码中就是这里做了限制，2147483647L就是2G字节数，所以如果遇到超过2G的，需要循环遍历，处理position和count
		 * if (var6 - var1 < (long)var8) {
		 *      var8 = (int)(var6 - var1);
		 * }
		 */

	}

	private static void transferToTest4() {
		try(RandomAccessFile srcFile = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_src").getPath()),"rw");
		    FileChannel srcFileChannel = srcFile.getChannel();
		    RandomAccessFile dstFile = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_dst").getPath()),"rw");
		    FileChannel dstFileChannel = dstFile.getChannel()) {

			dstFileChannel.write(ByteBuffer.wrap("********************".getBytes()));

			dstFileChannel.position(10);
			System.out.printf("srcFileChannel position = %s, size = %s\r\n", srcFileChannel.position(), srcFileChannel.size());
			System.out.printf("dstFileChannel position = %s, size = %s\r\n", dstFileChannel.position(), dstFileChannel.size());

			System.out.println("before transferTo");
			srcFileChannel.transferTo(2, 4, dstFileChannel);
			System.out.println("after transferTo");

			System.out.printf("after transferTo srcFileChannel position = %s, size = %s\r\n", srcFileChannel.position(), srcFileChannel.size());
			System.out.printf("after transferTo dstFileChannel position = %s, size = %s\r\n", dstFileChannel.position(), dstFileChannel.size());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void transferToTest3() {
		try(RandomAccessFile srcFile = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_src").getPath()),"rw");
		    FileChannel srcFileChannel = srcFile.getChannel();
		    RandomAccessFile dstFile = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_dst").getPath()),"rw");
		    FileChannel dstFileChannel = dstFile.getChannel()) {

			// dstFileChannel.position(10);
			System.out.printf("srcFileChannel position = %s, size = %s\r\n", srcFileChannel.position(), srcFileChannel.size());
			System.out.printf("dstFileChannel position = %s, size = %s\r\n", dstFileChannel.position(), dstFileChannel.size());

			System.out.println("before transferTo");
			long l = srcFileChannel.transferTo(2, 100, dstFileChannel);
			System.out.println("transferTo result = " + l);
			System.out.println("after transferTo");

			System.out.printf("after transferTo srcFileChannel position = %s, size = %s\r\n", srcFileChannel.position(), srcFileChannel.size());
			System.out.printf("after transferTo dstFileChannel position = %s, size = %s\r\n", dstFileChannel.position(), dstFileChannel.size());


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public static void transferToTest2() {
		try(RandomAccessFile srcFile = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_src").getPath()),"rw");
		    FileChannel srcFileChannel = srcFile.getChannel();
		    RandomAccessFile dstFile = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_dst").getPath()),"rw");
		    FileChannel dstFileChannel = dstFile.getChannel()) {

			dstFileChannel.position(8);
			System.out.println("before transferTo");
			long l = srcFileChannel.transferTo(2, 4, dstFileChannel);
			System.out.println("transferTo result = " + l);
			System.out.println("after transferTo");


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void transferToTest1() {
		try(FileChannel srcFileChannel = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_src").getPath()),"rw").getChannel();
		    FileChannel dstFileChannel = new RandomAccessFile(new File(_11_FileChannel_TransferTo.class.getClassLoader().getResource("channel/_11_FileChannel_transferTo_dst").getPath()),"rw").getChannel()) {

			dstFileChannel.position(8);
			srcFileChannel.transferTo(1000, 4, dstFileChannel);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
