package com.smxknife.java2.nio.channel;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 * @author smxknife
 * 2020/10/2
 */
public class _16_FileChannel_Open {
	public static void main(String[] args) {

		// FileChannel open(Path path, OpenOptions... options ) 打开一个文件
		/** OpenOptions的一个实现是：StandardOenOption
		 * 1. CREATE
		 *      创建一个新文件（如果不存在），该选项只是一个创建文件的意图，并不真正进行文件创建，如果CREATE之后，直接访问就会抛出NoSuchFileException
		 * 2. WRITE
		 *      打开进行写入访问，因为CREATE不能单独使用，一般配合WRITE使用
		 * 3. APPEND
		 *      打开文件从末尾追加
		 * 4. READ
		 *      打开进行读取访问
		 * 5. TRUNCATE_EXIST
		 *      如果文件已存在，并且为写入访问而打开，则其长度被截断为0
		 *      如果只为读取访问打开文件，则忽略此选项
		 * 6. CREATE_NEW
		 *      创建一个新文件，如果已经存在，则失败
		 *      如果CREATE_NEW与CREATE同时存在，忽略CREATE_NEW
		 * 7. DELETE_ON_CLOSE
		 *      关闭时删除
		 *      尽最大努力在关闭时尝试适当的close方法删除该文件。
		 *      如果未调用close方法，则在虚拟机终止时尝试删除该文件
		 * 8. SPARSE
		 *      稀疏文件
		 *      与CREATE_NEW一起使用时，此选项作为一个提示，表明新文件将是稀疏的。当文件操作系统不支持稀疏文件时，该选项将被忽略
		 *      在介绍稀疏文件前，先看一下普通文件存储时，磁盘占用情况
		 *          普通文件如果设置文件的size很大，如fileChannel.position(Long.MAX_VALUE)，然后只在最后存一个字符fileChannel.write(ByteBuffer.wrap("a".getBytes()))
		 *          这种情况下，虽然文件只有一个a字符，但是文件依然占用了很大空间。这样极大的浪费了磁盘的利用空间，为了提升磁盘的利用率，对那些不存储数据的空间先不占用磁盘空间，等有需要的时候再占用，文件大小依然很大，但是磁盘占用就很小，这其实就是稀疏文件
		 *      注意：稀疏文件的创建不能和CREATE一起，必须是CREATE_NEW一起
		 * 9. SYNC
		 *      要求对文件内容和元数据的每次更新都写入底层的存储设备
		 *      这样做程序的运行效率会降低
		 * 10. DSYNC
		 *      对文件内容的每次更新都同步写入底层的存储设备
		 *      与SYNC的区别是，SYNC同步文件内容和元数据，同force(true)；DSYNC只写入文件内容，同force(false)
		 */

		// 单独CREATE抛出异常
		// openTest1();

		// CREATE和WRITE，不会抛出异常，会创建文件
		// openTest2();

		// CREATE和WRITE，加正常写入数据，每次都是从头开始覆盖写入对应位置的字节
		// openTest3();

		// APPEND，当文件不存在时，会抛出异常
		// openTest4();

		// APPEND，当文件存在时
		// openTest5();

		// READ，读取测试
		// openTest6();

		// READ，写入测试,抛出NonWritableChannelException
		// openTest7();

		// TRUNCATE_EXIST 和WRITE测试
		// openTest8();

		// TRUNCATE_EXIST 和 READ测试
		// openTest9();

		// CREATE_NEW 单独使用已经存在的文件，没有任何影响
		// openTest10();

		// CREATE_NEW和WRITE使用，已经存在的文件，抛出FileAlreadyExistsException异常
		// openTest11();

		// CREATE_NEW 单独使用，不存在的文件，同CREATE一样，会抛出异常
		// openTest12();

		// CREATE_NEW 和 WRITE 使用，不存在的文件
		// openTest13();

		// DELETE_ON_CLOSE
		// openTest14();




	}

	private static void openTest14() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open_1");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.DELETE_ON_CLOSE);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest13() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open_1");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest12() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open_1");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE_NEW);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest11() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE_NEW, StandardOpenOption.WRITE);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest10() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE_NEW);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest9() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.READ);
			ByteBuffer byteBuffer = ByteBuffer.allocate(10);
			channel.read(byteBuffer);
			System.out.println(new String(byteBuffer.array()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest8() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
			channel.write(ByteBuffer.wrap("hello".getBytes()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest7() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			channel.read(byteBuffer);
			System.out.println(new String(byteBuffer.array()));
			channel.write(ByteBuffer.wrap("hello".getBytes()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest6() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.READ);
			ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
			channel.read(byteBuffer);
			System.out.println(new String(byteBuffer.array()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest5() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.APPEND);
			channel.write(ByteBuffer.wrap("abcdef".getBytes()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest4() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open1");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.APPEND);
			channel.write(ByteBuffer.wrap("********".getBytes()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest3() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			channel.write(ByteBuffer.wrap("********".getBytes()));
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest2() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void openTest1() {
		File file = new File(_16_FileChannel_Open.class.getClassLoader().getResource("channel").getPath(), "_16_FileChannel_open");
		Path path = file.toPath();
		try {
			FileChannel channel = FileChannel.open(path, StandardOpenOption.CREATE);
			channel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
