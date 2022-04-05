package com.smxknife.java2.nio.channel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _12_FileChannel_TransferFrom {
	public static void main(String[] args) {

		// 将字节从给定的可读字节通道传输到此通道的文件中，相当于read，只不过是将通道中的数据传输到另一个通道中，而不是缓冲区
		// 与transferTo不同的是transferFrom，不能使FileChannel对应的文件大小增长
		/**
		 * count: 试着从源通道中最多读取count个字节
		 *      * 如果源通道剩余空间小于count，或者如果源通道是非阻塞的并且其输入缓冲区中可用的空间小于count个字节，则所传输的字节数要小于请求的字节数
		 * position: 将count个字节写入此通道文件中从给定的position处开始位置
		 *      * 不修改position
		 *      * 如果给定的位置大于该文件的当前大小，则不传输任何字节
		 *      * 从源通道中当前位置开始读取个字节写入到当前通道，然后将src通道的位置增加读取的字节数
		 */
		// 给定位置大于该文件当前大小，则不传输任何字节
		// transferFromTest1();

		// 正常数据传输
		// transferFromTest2();

		// 验证如果count的字节个数大于src.remaining，则通道的src.remaining字节数传输到当前通道的position位置
		// transferFromTest3();

	}

	private static void transferFromTest3() {
		try(FileChannel srcFileChannel = new RandomAccessFile(new File(_12_FileChannel_TransferFrom.class.getClassLoader().getResource("channel/_12_FileChannel_transferFrom_src").getPath()),"rw").getChannel();
		    FileChannel dstFileChannel = new RandomAccessFile(new File(_12_FileChannel_TransferFrom.class.getClassLoader().getResource("channel/_12_FileChannel_transferFrom_dst").getPath()),"rw").getChannel()) {

			srcFileChannel.position(3);
			long l = dstFileChannel.transferFrom(srcFileChannel, 1, 3); // 这里关注count，为3，而上面设置srcFileChannel的position是3，而整个文件的长度为5，position+count=6，大于5，看输出结果
			System.out.println("transferFrom result = " + l); // 这里的返回结果是2，说明虽然count为3，但是srcFileChannel的remaining为2，那么传输的结果为2
			System.out.printf("dstFileChannel position = %s, size = %s\r\n", dstFileChannel.position(), dstFileChannel.size());
			System.out.printf("srcFileChannel position = %s, size = %s\r\n", srcFileChannel.position(), srcFileChannel.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void transferFromTest2() {
		try(FileChannel srcFileChannel = new RandomAccessFile(new File(_12_FileChannel_TransferFrom.class.getClassLoader().getResource("channel/_12_FileChannel_transferFrom_src").getPath()),"rw").getChannel();
		    FileChannel dstFileChannel = new RandomAccessFile(new File(_12_FileChannel_TransferFrom.class.getClassLoader().getResource("channel/_12_FileChannel_transferFrom_dst").getPath()),"rw").getChannel()) {

			dstFileChannel.position(8); // 虽然这里设置了dst的position是8，但是仍然会从transferFrom指定的position是1开始写入
			long l = dstFileChannel.transferFrom(srcFileChannel, 1, 3);// position代表的是dstFileChannel所对应文件的position，如果超过文件大小，不传输任何字节，这也说明了不会使文件增长
			System.out.println("transferFrom result = " + l);
			System.out.printf("dstFileChannel position = %s, size = %s\r\n", dstFileChannel.position(), dstFileChannel.size()); // 这里验证了，即使写入了，也不会更改position的位置，依然是8
			System.out.printf("srcFileChannel position = %s, size = %s\r\n", srcFileChannel.position(), srcFileChannel.size());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void transferFromTest1() {
		try(FileChannel srcFileChannel = new RandomAccessFile(new File(_12_FileChannel_TransferFrom.class.getClassLoader().getResource("channel/_12_FileChannel_transferFrom_src").getPath()),"rw").getChannel();
		    FileChannel dstFileChannel = new RandomAccessFile(new File(_12_FileChannel_TransferFrom.class.getClassLoader().getResource("channel/_12_FileChannel_transferFrom_dst").getPath()),"rw").getChannel()) {

			dstFileChannel.position(8);
			dstFileChannel.transferFrom(srcFileChannel, 100, 2);// position代表的是dstFileChannel所对应文件的position，如果超过文件大小，不传输任何字节，这也说明了不会使文件增长

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
