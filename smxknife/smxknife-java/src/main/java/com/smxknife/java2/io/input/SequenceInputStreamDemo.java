package com.smxknife.java2.io.input;

import java.io.*;
import java.util.*;

/**
 * @author smxknife
 * 2018/11/15
 */
public class SequenceInputStreamDemo {
	public static void main(String[] args) throws IOException {

		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
		StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("StringBufferInputStream content");

		File rootPath = new File("");
		String filePath = rootPath.getAbsolutePath() + "/src/main/java/com/smxknife/java2/io/input/FileInputStreamDemo.java";
		File file = new File(filePath);
		FileInputStream fileInputStream = new FileInputStream(filePath);


		List<InputStream> inputStreams = Arrays.asList(byteArrayInputStream, stringBufferInputStream, fileInputStream);
		Enumeration<InputStream> enumeration1 = Collections.enumeration(inputStreams);

		SequenceInputStream sequenceInputStream = new SequenceInputStream(enumeration1);

		byte[] bytes = new byte[300];

		// 用法1 循环输出所有流的数据
//		int len = 0;
//		while ((len = sequenceInputStream.read(bytes)) != -1) {
//			System.out.println(new String(bytes, 0, bytes.length));
//		}

		// 用法2 读第一个非空流数据
//		int read = sequenceInputStream.read(bytes, 0, bytes.length);
//		System.out.println(new String(bytes));

		// 用法3 读第一个非空数据
//		int read = sequenceInputStream.read();
//		System.out.println((char) read);

		// 用法4 读取所有数据
		int data = 0;
		while ((data = sequenceInputStream.read()) != -1) {
			System.out.print((char) data);
		}
		sequenceInputStream.close();
	}
}

class InputStreamEnumeration {

	private List<InputStream> inputStreams = new ArrayList<>();

	public void add(InputStream inputStream) {
		inputStreams.add(inputStream);
	}

	public Enumeration<InputStream> elements() {
		return new Enumeration<InputStream>() {

			int index = 0;

			@Override
			public boolean hasMoreElements() {
				return index < inputStreams.size();
			}

			@Override
			public InputStream nextElement() {
				synchronized (InputStreamEnumeration.this) {
					if (index < inputStreams.size()) {
						return inputStreams.get(index++);
					}
				}
				throw new NoSuchElementException("InputStream Enumeration");
			}
		};
	}
}
