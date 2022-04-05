package com.smxknife.java2.nio.channel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _15_FileChannel_Map {
	public static void main(String[] args) {

		// MappedByteBuffer map(FileChannel.MapModel, long position, long size)将此通道文件区域直接映射到内存中，可以通过以下三种模式：
		/**
		 * 1. 只读：（MapModel.READ_ONLY）
		 *      试图修改缓冲区将抛出异常ReadOnlyBufferException
		 * 2. 读取写入：（MapModel.READ_WRITE）
		 *      对缓冲区的更改将传播到文件，并且该更改对映射到同一文件的其他程序是不一定是可见的
		 * 3. 专用：（MapModel.PRIVATE，写时复制）
		 *      对缓冲区的更改不会传播到文件，并且该更改对映射到同一文件的其他程序是不可见的。相反，会创建缓冲区已修改部分的副本
		 */
		// 返回的缓冲区MappedByteBuffer，position为零，限制和容量为size，标记不确定；在缓冲区本身被垃圾回收之前，缓冲区表示的映射关系都是有效的
		// 映射关系一经创建，就不再依赖于创建它时所用的文件通道。关闭通道对映射关系没有任何的影响

		// 当请求的区域没有完全包含在此通道的文件中时，此方法的行为是未指定的：未指定是否将此程序或另一程序对底层文件内容或大小的修改传播到缓冲区；未指定将对缓冲区的修改传播到文件的频率
		// 相对于普通的read和write方法来说，map（将文件映射到内存）开销更大，通常将较大文件映射到内存才值得

		// map api测试
		// mapTest1();

		// READ_ONLY测试
		// mapTest2();

		// READ_WRITE测试
		// mapTest3();

		// PRIVATE测试
		// mapTest4();

		// 测试load和isLoad
		loadTest5();


	}

	private static void loadTest5() {
		try(FileChannel channel = new RandomAccessFile(_15_FileChannel_Map.class.getClassLoader()
				.getResource("channel/_15_FileChannel_map").getPath(), "rw").getChannel()) {

			MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
			System.out.println(map.isLoaded());
			map.load();
			System.out.println(map.isLoaded());



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mapTest4() {
		try(FileChannel channel = new RandomAccessFile(_15_FileChannel_Map.class.getClassLoader()
				.getResource("channel/_15_FileChannel_map").getPath(), "rw").getChannel()) {

			MappedByteBuffer map = channel.map(FileChannel.MapMode.PRIVATE, 0, 5);
			System.out.printf("A MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());

			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());

			System.out.println("----------------------------------");

			map.rewind();
			System.out.printf("B after rewind MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());
			map.put((byte) 'a'); // 这里put的时候，如果不加单引号，会转义成其他字节内容
			map.put((byte) 'b');
			map.put((byte) 'c');
			map.put((byte) 'd');
			map.put((byte) 'e');
			System.out.printf("C after 5 times put operation MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());

			map.force(); // 对于Private即使是force调用，也不会写入磁盘


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mapTest3() {
		try(FileChannel channel = new RandomAccessFile(_15_FileChannel_Map.class.getClassLoader()
				.getResource("channel/_15_FileChannel_map").getPath(), "rw").getChannel()) {

			MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_WRITE, 0, 5);
			System.out.printf("A MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());

			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());

			System.out.println("----------------------------------");

			map.rewind();
			System.out.printf("B after rewind MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());
			map.put((byte) 'a'); // 这里put的时候，如果不加单引号，会转义成其他字节内容
			map.put((byte) '1');
			map.put((byte) '2');
			map.put((byte) '3');
			map.put((byte) '4');
			System.out.printf("C after 5 times put operation MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());

			// TODO: 经过测试最终结果没有反馈到硬盘上，依然是原始的文件内容

			 map.force(); // 这里调用force是因为在mac上上面写入的内容在程序结束时，没有写到硬盘文件，所以这里在程序结束时强制刷新到文件
			// TimeUnit.SECONDS.sleep(15); // 这里采用放大程序执行时间的方式依然无法让内存的修改内容持久化到硬盘上

			// 下面将超出范围写入测试（测试结果是：抛出异常）
			// map.put((byte) 5);
			// map.put((byte) 6);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mapTest2() {
		try(FileChannel channel = new RandomAccessFile(_15_FileChannel_Map.class.getClassLoader()
				.getResource("channel/_15_FileChannel_map").getPath(), "rw").getChannel()) {

			MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, 5);
			map.put((byte) 1); // 抛出异常

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void mapTest1() {
		try(FileChannel channel = new RandomAccessFile(_15_FileChannel_Map.class.getClassLoader()
				.getResource("channel/_15_FileChannel_map").getPath(), "rw").getChannel()) {

			System.out.printf("channel position = %s, size = %s\r\n", channel.position(), channel.size()); // 最初的文件是空文件
			MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, 5); // 即使是空文件，在映射的时候，会将0到4之间的字节映射为未指定（NIL）
			System.out.printf("A MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());

			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());

			System.out.println("----------------------------------");

			map = channel.map(FileChannel.MapMode.READ_ONLY, 2, 2);
			System.out.printf("A MappedByteBuffer position = %s, limit = %s, capacity = %s\r\n", map.position(), map.limit(), map.capacity());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position());
			System.out.printf("MappedByteBuffer get = %s, position = %s\r\n", (char)map.get(), map.position()); // 超出范围将抛出异常

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
