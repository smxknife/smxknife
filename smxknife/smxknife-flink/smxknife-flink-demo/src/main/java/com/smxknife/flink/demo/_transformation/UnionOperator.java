package com.smxknife.flink.demo._transformation;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 *
 * @author smxknife
 * 2021/6/3
 */
public class UnionOperator {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

		final DataStreamSource<Integer> stream0 = executionEnvironment.fromElements(1, 2, 3, 4, 5, 6);
		final DataStreamSource<Tuple2<String, Integer>> stream1 = executionEnvironment.fromElements(Tuple2.of("kafka", 3), Tuple2.of("java", 4));
		final DataStreamSource<Tuple2<String, Integer>> stream2 = executionEnvironment.fromElements(Tuple2.of("hello", 1), Tuple2.of("flink", 2));

		/**
		 * Union可以将两个数据流合并，合并的条件数据流中的元素类型必须一致，否则不行
		 */

		// 因为类型不一致，编译错误
//		stream2.union(stream0);

		stream2.union(stream1).print();

		executionEnvironment.execute();
	}
}
