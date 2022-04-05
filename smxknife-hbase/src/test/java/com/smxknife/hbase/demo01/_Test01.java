package com.smxknife.hbase.demo01;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.*;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.SingleColumnValueFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Iterator;

/**
 * @author smxknife
 * 2021/5/23
 */
public class _Test01 {

	Configuration configuration = null;
	Connection conn = null;
	// 表的管理对象
	Admin admin = null;
	// 表名对象
	TableName tableName = TableName.valueOf("energy");

	// 数据操作对象
	Table table = null;

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

	@Test
	public void scanAggre() {

	}

	@Test
	public void scanWithFilter() throws IOException {
		Scan scan = new Scan();
		// 创建过滤器集合
		FilterList filters = new FilterList(FilterList.Operator.MUST_PASS_ALL);
		// 单列过滤
		final SingleColumnValueFilter singleColumnValueFilter = new SingleColumnValueFilter(Bytes.toBytes("industry"),
				Bytes.toBytes("industryCode"), CompareOperator.EQUAL, Bytes.toBytes("C31"));
		filters.addFilter(singleColumnValueFilter);

		// 前缀过滤器
		PrefixFilter prefixFilter = new PrefixFilter(Bytes.toBytes("111"));
		filters.addFilter(prefixFilter);

		scan.setFilter(filters);

		final ResultScanner scanner = table.getScanner(scan);
		final Iterator<Result> iterator = scanner.iterator();

		while (iterator.hasNext()) {
			final Result result = iterator.next();
			final Cell energyCode = result.getColumnLatestCell(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"));
			final Cell regionCode = result.getColumnLatestCell(Bytes.toBytes("region"), Bytes.toBytes("regionCode"));
			final Cell industryCode = result.getColumnLatestCell(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"));
			final Cell domainCode = result.getColumnLatestCell(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"));
			final Cell dataCode = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataCode"));
			final Cell dataValue = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataValue"));
			final Cell dataTime = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataTime"));

			System.out.printf("energyCode: %s, regionCode: %s, industryCode: %s, domainCode: %s, dataCode: %s, dataValue: %s, dataTime: %s\r\n------------------------\r\n",
					Bytes.toInt(CellUtil.cloneValue(energyCode)),
					Bytes.toString(CellUtil.cloneValue(regionCode)),
					Bytes.toString(CellUtil.cloneValue(industryCode)),
					Bytes.toString(CellUtil.cloneValue(domainCode)),
					Bytes.toString(CellUtil.cloneValue(dataCode)),
					Bytes.toDouble(CellUtil.cloneValue(dataValue)),
					LocalDateTime.ofInstant(Instant.ofEpochSecond(Bytes.toLong(CellUtil.cloneValue(dataTime))), ZoneId.systemDefault())
			);
		}
	}

	@Test
	public void scanCondition() throws IOException {
		Scan scan = new Scan();
		String startRow = "1112";
		String stopRow = "3333";
		// 从这里可以看出，hbase的rowkey是有多么重要来，所以需要对rowkey进行好好的设计才会有很高的性能
		scan.withStartRow(Bytes.toBytes(startRow));
		scan.withStopRow(Bytes.toBytes(stopRow));

		final ResultScanner scanner = table.getScanner(scan);
		final Iterator<Result> iterator = scanner.iterator();
		while (iterator.hasNext()) {
			final Result result = iterator.next();
			final Cell energyCode = result.getColumnLatestCell(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"));
			final Cell regionCode = result.getColumnLatestCell(Bytes.toBytes("region"), Bytes.toBytes("regionCode"));
			final Cell industryCode = result.getColumnLatestCell(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"));
			final Cell domainCode = result.getColumnLatestCell(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"));
			final Cell dataCode = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataCode"));
			final Cell dataValue = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataValue"));
			final Cell dataTime = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataTime"));

			System.out.printf("energyCode: %s, regionCode: %s, industryCode: %s, domainCode: %s, dataCode: %s, dataValue: %s, dataTime: %s\r\n------------------------\r\n",
					Bytes.toInt(CellUtil.cloneValue(energyCode)),
					Bytes.toString(CellUtil.cloneValue(regionCode)),
					Bytes.toString(CellUtil.cloneValue(industryCode)),
					Bytes.toString(CellUtil.cloneValue(domainCode)),
					Bytes.toString(CellUtil.cloneValue(dataCode)),
					Bytes.toDouble(CellUtil.cloneValue(dataValue)),
					LocalDateTime.ofInstant(Instant.ofEpochSecond(Bytes.toLong(CellUtil.cloneValue(dataTime))), ZoneId.systemDefault())
			);
		}
	}

	@Test
	public void scan() throws IOException {
		Scan scan = new Scan();
		final ResultScanner scanner = table.getScanner(scan);
		final Iterator<Result> iterator = scanner.iterator();
		while (iterator.hasNext()) {
			final Result result = iterator.next();
			final Cell energyCode = result.getColumnLatestCell(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"));
			final Cell regionCode = result.getColumnLatestCell(Bytes.toBytes("region"), Bytes.toBytes("regionCode"));
			final Cell industryCode = result.getColumnLatestCell(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"));
			final Cell domainCode = result.getColumnLatestCell(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"));
			final Cell dataCode = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataCode"));
			final Cell dataValue = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataValue"));
			final Cell dataTime = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataTime"));

			System.out.printf("energyCode: %s, regionCode: %s, industryCode: %s, domainCode: %s, dataCode: %s, dataValue: %s, dataTime: %s\r\n------------------------\r\n",
					Bytes.toInt(CellUtil.cloneValue(energyCode)),
					Bytes.toString(CellUtil.cloneValue(regionCode)),
					Bytes.toString(CellUtil.cloneValue(industryCode)),
					Bytes.toString(CellUtil.cloneValue(domainCode)),
					Bytes.toString(CellUtil.cloneValue(dataCode)),
					Bytes.toDouble(CellUtil.cloneValue(dataValue)),
					LocalDateTime.ofInstant(Instant.ofEpochSecond(Bytes.toLong(CellUtil.cloneValue(dataTime))), ZoneId.systemDefault())
			);
		}
	}

	@Test
	public void get() throws IOException {
		Get get = new Get(Bytes.toBytes("1111"));

		// 下面这种取法是会取出所有的数据，如果在数据量很大时，会产生很多额外的io，如果我们只要data里面的dataValue呢
		// -----------------------
		//    限制取出的列
		//get.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataValue"));
		// 通过上面这限制，下面会报错

		// -----------------------
		final Result result = table.get(get);
		final Cell energyCode = result.getColumnLatestCell(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"));
		final Cell regionCode = result.getColumnLatestCell(Bytes.toBytes("region"), Bytes.toBytes("regionCode"));
		final Cell industryCode = result.getColumnLatestCell(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"));
		final Cell domainCode = result.getColumnLatestCell(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"));
		final Cell dataCode = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataCode"));
		final Cell dataValue = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataValue"));
		final Cell dataTime = result.getColumnLatestCell(Bytes.toBytes("data"), Bytes.toBytes("dataTime"));

		System.out.printf("energyCode: %s, regionCode: %s, industryCode: %s, domainCode: %s, dataCode: %s, dataValue: %s, dataTime: %s\r\n",
				Bytes.toInt(CellUtil.cloneValue(energyCode)),
				Bytes.toString(CellUtil.cloneValue(regionCode)),
				Bytes.toString(CellUtil.cloneValue(industryCode)),
				Bytes.toString(CellUtil.cloneValue(domainCode)),
				Bytes.toString(CellUtil.cloneValue(dataCode)),
				Bytes.toDouble(CellUtil.cloneValue(dataValue)),
				LocalDateTime.ofInstant(Instant.ofEpochSecond(Bytes.toLong(CellUtil.cloneValue(dataTime))), ZoneId.systemDefault())
		);
	}

	@Test
	public void delete() throws IOException {
		Delete delete = new Delete(Bytes.toBytes("1111"));
		table.delete(delete);
	}

	@Test
	public void insert() throws IOException {
		// 构造里面指定rowkey
//		Put put = new Put(Bytes.toBytes("1111"));
//		put.addColumn(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"), Bytes.toBytes(3300));
//		put.addColumn(Bytes.toBytes("region"), Bytes.toBytes("regionCode"), Bytes.toBytes("330100"));
//		put.addColumn(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"), Bytes.toBytes("C31"));
//		put.addColumn(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"), Bytes.toBytes("100"));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataCode"), Bytes.toBytes("0000"));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataValue"), Bytes.toBytes(10.5));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataTime"), Bytes.toBytes(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()));

		// 第二条数据
//		Put put = new Put(Bytes.toBytes("1112"));
//		put.addColumn(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"), Bytes.toBytes(2100));
//		put.addColumn(Bytes.toBytes("region"), Bytes.toBytes("regionCode"), Bytes.toBytes("330200"));
//		put.addColumn(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"), Bytes.toBytes("C32"));
//		put.addColumn(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"), Bytes.toBytes("100"));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataCode"), Bytes.toBytes("0000"));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataValue"), Bytes.toBytes(20.5));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataTime"), Bytes.toBytes(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()));

		// 第三条数据
//		Put put = new Put(Bytes.toBytes("1113"));
//		put.addColumn(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"), Bytes.toBytes(2100));
//		put.addColumn(Bytes.toBytes("region"), Bytes.toBytes("regionCode"), Bytes.toBytes("330100"));
//		put.addColumn(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"), Bytes.toBytes("C31"));
//		put.addColumn(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"), Bytes.toBytes("100"));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataCode"), Bytes.toBytes("0000"));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataValue"), Bytes.toBytes(30.5));
//		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataTime"), Bytes.toBytes(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()));

		// 第四条数据
		Put put = new Put(Bytes.toBytes("2000"));
		put.addColumn(Bytes.toBytes("energy"), Bytes.toBytes("energyCode"), Bytes.toBytes(2100));
		put.addColumn(Bytes.toBytes("region"), Bytes.toBytes("regionCode"), Bytes.toBytes("330100"));
		put.addColumn(Bytes.toBytes("industry"), Bytes.toBytes("industryCode"), Bytes.toBytes("C31"));
		put.addColumn(Bytes.toBytes("domain"), Bytes.toBytes("domainCode"), Bytes.toBytes("100"));
		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataCode"), Bytes.toBytes("0000"));
		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataValue"), Bytes.toBytes(30.5));
		put.addColumn(Bytes.toBytes("data"), Bytes.toBytes("dataTime"), Bytes.toBytes(LocalDateTime.now().atZone(ZoneId.systemDefault()).toEpochSecond()));

		table.put(put);
	}


	@Test
	public void createTable() throws IOException {
		// 定义表描述对象
		final TableDescriptorBuilder tableDescriptorBuilder = TableDescriptorBuilder.newBuilder(tableName);
		// 定义列族描述对象
		// TODO 这个设计非常不好，一个表中的列族最好控制在两个以内，如果一个够了最好
		final ColumnFamilyDescriptorBuilder energyBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("energy"));
		final ColumnFamilyDescriptorBuilder regionBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("region"));
		final ColumnFamilyDescriptorBuilder industryBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("industry"));
		final ColumnFamilyDescriptorBuilder domainBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("domain"));
		final ColumnFamilyDescriptorBuilder dataBuilder = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("data"));
		// 添加列族信息给表
		tableDescriptorBuilder.setColumnFamilies(Arrays.asList(energyBuilder.build(), regionBuilder.build(),
				industryBuilder.build(), domainBuilder.build(), dataBuilder.build()));
		if (admin.tableExists(tableName)) {
			// 这里可以删除表
			admin.disableTable(tableName);
			admin.deleteTable(tableName);
		}
		// 创建表
		admin.createTable(tableDescriptorBuilder.build());
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
