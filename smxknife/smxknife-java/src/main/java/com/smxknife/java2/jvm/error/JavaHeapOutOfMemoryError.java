package com.smxknife.java2.jvm.error;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟java堆OutOfMemoryError，前提是需要设置参数，防止虚拟机进行内存扩展 -Xmx128K -Xms128K
 * 通过命令来运行class文件
 * 1) cd target/classes
 * 2) java -cp . -Xmx1024K -Xms1024K com.smxknife.java2.jvm.error.JavaStackOutOfMemoryError
 * 通过指定-Xmx和-Xms来防止java进行内存扩展
 * 最后输出结果<p>Exception in thread "main" java.lang.OutOfMemoryError: Java heap space</p>
 * @author smxknife
 * 2019-06-14
 */
public class JavaHeapOutOfMemoryError {
	public static void main(String[] args) {
		List<OOMObject> objects = new ArrayList<>();

		while (true) {
			System.out.println(objects.size());
			objects.add(new OOMObject());
		}

	}
}
