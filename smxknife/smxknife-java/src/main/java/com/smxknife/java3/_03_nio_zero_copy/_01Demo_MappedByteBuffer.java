package com.smxknife.java3._03_nio_zero_copy;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/3/18
 */
public class _01Demo_MappedByteBuffer {

	private final static String CONTENT = "Zero copy implemented by MappedByteBuffer";
	private final static String FILE_NAME = "mmap.txt";
	private final static String CHARSET = "UTF-8";

	public static void main(String[] args) {
		_01Demo_MappedByteBuffer demo = new _01Demo_MappedByteBuffer();
		// 写
		demo.writeToFileByMappedByteBuffer();
		// 读
		demo.readFromFileByMappedByteBuffer();
	}

	private void readFromFileByMappedByteBuffer() {
		// 读文件数据：打开文件通道 fileChannel 并提供只读权限，通过 fileChannel 映射到一个只可读的内存缓冲区 mappedByteBuffer，读取 mappedByteBuffer 中的字节数组即可得到文件数据
		Path path = Paths.get(this.getClass().getResource(FILE_NAME).getPath());
		byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
		try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ)) {
			MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, bytes.length);
			if (Objects.nonNull(mappedByteBuffer)) {
				byte[] data = new byte[bytes.length];
				mappedByteBuffer.get(data);
				String readContent = new String(data, StandardCharsets.UTF_8);
				System.out.println("readContent = " + readContent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void writeToFileByMappedByteBuffer() {
		// 写文件数据：打开文件通道 fileChannel 并提供读权限、写权限和数据清空权限，通过 fileChannel 映射到一个可写的内存缓冲区 mappedByteBuffer，将目标数据写入 mappedByteBuffer，通过 force() 方法把缓冲区更改的内容强制写入本地文件
		Path path = Paths.get(this.getClass().getResource(FILE_NAME).getPath());
		byte[] bytes = CONTENT.getBytes(Charset.forName(CHARSET));
		try (FileChannel fileChannel = FileChannel.open(path, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
			MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);
			if (Objects.nonNull(mappedByteBuffer)) {
				mappedByteBuffer.put(bytes);
				mappedByteBuffer.force();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
