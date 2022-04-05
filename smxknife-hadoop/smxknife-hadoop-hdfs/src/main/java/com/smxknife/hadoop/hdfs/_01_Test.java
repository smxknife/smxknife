package com.smxknife.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author smxknife
 * 2021/7/4
 */
public class _01_Test {

	private Configuration conf = null;
	private FileSystem fs = null;

	@Before
	public void conn() throws IOException {
		// true表示加载配置文件，core-site.xml 、hdfs-site.xml
		conf = new Configuration(true);
		// 返回的是DistributeFileSystem，参考的是core-site.xml的配置
		/*
		<property>
		    <name>fs.defaultFS</name>
		    <value>hdfs://smxknife.local:9000</value>
		</property>
		如果是hdfs: 表示创建的是DistributeFileSystem
		 */
		fs = FileSystem.get(conf);
	}

	@Test
	public void makeDir() throws IOException {
		Path dir = new Path("/msb");
		if (fs.exists(dir)) {
			fs.delete(dir, true);
		}
		fs.mkdirs(dir);
	}

	@Test
	public void upload() {
		Path file = new Path("/msb/hello");
		try(BufferedInputStream bis = new BufferedInputStream(new FileInputStream("./data/Hello"))) {
			final FSDataOutputStream fsDataOutputStream = fs.create(file);

			IOUtils.copyBytes(bis, fsDataOutputStream, conf, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void blocks() throws IOException {
		Path file = new Path("/user/ShaoYun/data.txt");
		final FileStatus fileStatus = fs.getFileStatus(file);
		final BlockLocation[] blockLocations = fs.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
		for (BlockLocation blockLocation : blockLocations) {
			System.out.println(blockLocation);
		}

		final FSDataInputStream fsDataInputStream = fs.open(file);

		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());

		System.out.println("-------------------");
		fsDataInputStream.seek(1048576);
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
		System.out.println((char)fsDataInputStream.readByte());
	}

	@After
	public void close() throws IOException {
		fs.close();
	}
}
