package com.smxknife.hbase.demo02;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author smxknife
 * 2021/5/23
 */
public class _Test02_Protobuf {

	Configuration configuration = null;
	Connection conn = null;
	// 表的管理对象
	Admin admin = null;
	// 表名对象
	TableName tableName = TableName.valueOf("ent_energy_dws");

	// 数据操作对象
	Table table = null;


	@Test
	public void insert() throws IOException {
		// 构造里面指定rowkey

		// 这种方式可以作为冷数据进行处理，可以有效的压缩数据大小
		// 因为这种方式无法使用filter进行数据过滤
//		final EnergyDws.EnergyData energyData = EnergyDws.EnergyData.newBuilder()
//				.setEnergyCode("2100")
//				.setRegionCode("330100")
//				.setIndustryCode("C31")
//				.setDataCode("0000")
//				.setDataValue(40.0)
//				.setDataTime(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()).build();
//		Put put = new Put(Bytes.toBytes("2000"));
//		// 这种情况下，就没办法分成不同的列族，只能用一个列族
//		put.addColumn(Bytes.toBytes("energy_data"), Bytes.toBytes("data"), energyData.toByteArray());
//		table.put(put);
	}

	@Test
	public void test() {
		final String format = String.format("%018d", 1);
		System.out.println(format);
	}

	@Test
	public void batchInsertNoProtobuf() throws IOException {
		/**
		 * rowkey = 时间 + energyCode + 企业代码
		 * 列族：energy
		 * - energyCode
		 * - regionCode
		 * - industryCode
		 * - domainCode
		 * - dataCode
		 * - dataValue
		 * - dataTime
		 */
		final byte[] energy = Bytes.toBytes("energy");
		final byte[] energyCode = Bytes.toBytes("energyCode");
		final byte[] regionCode = Bytes.toBytes("regionCode");
		final byte[] industryCode = Bytes.toBytes("industryCode");
		final byte[] dataValue = Bytes.toBytes("dataValue");
		final byte[] dataTime = Bytes.toBytes("dataTime");

		LocalDate end = LocalDate.now();
		LocalDate start = end.minusYears(1);
		List<Put> puts = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			final String entCode = String.format("%018d", i);
			final byte[] region = Bytes.toBytes(String.format("31%02d", i));
			byte[] industry = null;
			if (i % 2 == 0) {
				industry = Bytes.toBytes("C31");
			} else {
				industry = Bytes.toBytes("C33");
			}
			System.out.println("iiiiii");
			for (int j = 3300; j < 3305; j++) {
				System.out.println("jjjjjjj");
				for (LocalDate temp = start ;temp.isBefore(end);temp = temp.plusDays(1)) {
					final long time = start.atStartOfDay().atZone(ZoneId.systemDefault()).toEpochSecond();
					final String rowKey = (Long.MAX_VALUE - time) + "_" + j + "_" + entCode;
					Put put = new Put(Bytes.toBytes(rowKey));
					put.addColumn(energy, energyCode, Bytes.toBytes(String.valueOf(j)));
					put.addColumn(energy, regionCode, region);
					put.addColumn(energy, industryCode, industry);
					put.addColumn(energy, dataValue, Bytes.toBytes(ThreadLocalRandom.current().nextInt(10)));
					put.addColumn(energy, dataTime, Bytes.toBytes(time));
					puts.add(put);
					System.out.println("---- " + i + " _ " + j + " _ " + start);
				}

			}

		}
		table.put(puts);
		puts.clear();
	}




	@Test
	public void createTable() throws IOException {
		// 定义表描述对象
		final TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
		// 定义列族描述对象
		final ColumnFamilyDescriptorBuilder energyBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("energy"));
//		final ColumnFamilyDescriptorBuilder regionBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("region"));
//		final ColumnFamilyDescriptorBuilder industryBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("industry"));
//		final ColumnFamilyDescriptorBuilder domainBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("domain"));
//		final ColumnFamilyDescriptorBuilder dataBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("data"));
		// 添加列族信息给表
		tableDescriptorBuilder.setColumnFamilies(Arrays.asList(energyBuilder.build()));
		if (admin.tableExists(tableName)) {
			// 这里可以删除表
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		}
		// 创建表
		admin.createTable(tableDescriptorBuilder.build());
	}


	@Before
	public void init() throws IOException {
		// 创建配置文件对象
		configuration = HBaseConfiguration.create();
		// 加载zookeeper，从zk中获取hbase的配置
		configuration.set("hbase.zookeeper.quorum", "localhost:2181");
		// 获取连接
		conn = ConnectionFactory.createConnection(configuration);
		// 获取表管理对象
		admin = conn.getAdmin();
		// 获取数据操作对象
		table = conn.getTable(tableName);
	}

	@After
	public void destroy() {
		try {
			admin.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			table.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			conn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
