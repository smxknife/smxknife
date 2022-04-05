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
public class _14_FileChannel_Force {
	public static void main(String[] args) {

		// force(boolean metaData) 强制将所有对通道文件的更新写入包含文件的存储设备
		/**
		 * 如果此通道的文件驻留在本地存储设备，则此方法返回时可以保证：在此通道创建后或最后一次调用此方法后，对该文件进行的所有修改都已写入设备中。这对系统崩溃时不会丢失重要信息特别有用
		 * 如果通道文件不在本地，则无法提供保证
		 *
		 * metaData 用于限制此方法必须执行的IO操作数量，
		 *      作为false传入此方法时，只需要对文件内容的更新写入存储设备
		 *      作为true时，必须写入对文件内容和元数据的更新
		 *
		 *      通常需要一个以上的IO操作
		 *      是否实际有效，取决于底层操作系统
		 *
		 * 调用此方法可能导致IO操作，即使通道仅允许进行读取操作也是如此，可能会更新元数据，例如最后一次读取时间
		 * 其实操作系统在每次写入的时候，并没有把数据直接写入到磁盘，而是存放在操作系统内核的缓存中，减少硬盘的读写次数，然后在某一个时间点上将缓存的数据同步到硬盘上，但是同步的时间由操作系统来决定
		 * force就是强制操作系统进行一次存盘操作，这样减少系统崩溃导致的数据丢失的风险
		 */

		// 没有force的效率
		noForceWriteTest1();

		// 有force的效率
		withForceWriteTest2();

		// 参数metaData传入true，强制更新文件内容和元数据，false只更新文件内容。最终取决于操作系统
		// Linux系统的glibc库2.17版本中，两个方法一样，因为fdatasync与fsync一样，因为前者就是调用后者的，如果java代码在linux操作系统中进行测试，无论true还是false都会更新文件内容和元数据


	}

	private static void withForceWriteTest2() {
		System.out.println("------- with force --------");
		try(FileOutputStream fos = new FileOutputStream(new File(_14_FileChannel_Force.class.getClassLoader().getResource("channel/_14_FileChannel_force").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("abcdef".getBytes());
			long start = System.currentTimeMillis();
			for (int i = 0; i < 5000; i++) {
				channel.write(byteBuffer);
				channel.force(false);
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void noForceWriteTest1() {
		System.out.println("------- no force --------");
		try(FileOutputStream fos = new FileOutputStream(new File(_14_FileChannel_Force.class.getClassLoader().getResource("channel/_14_FileChannel_force").getPath()));
		    FileChannel channel = fos.getChannel();) {

			ByteBuffer byteBuffer = ByteBuffer.wrap("abcdef".getBytes());
			long start = System.currentTimeMillis();
			for (int i = 0; i < 5000; i++) {
				channel.write(byteBuffer);
			}
			long end = System.currentTimeMillis();
			System.out.println(end - start);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
