package com.smxknife.flink.demo.demo05;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author smxknife
 * 2020/9/8
 */
public class IterationDemo {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setParallelism(1);

		DataStreamSource<Long> incrDataStream = env.generateSequence(0, 1000);
		// 这里调用iterate返回IterativeStream
		IterativeStream<Long> iterateStream = incrDataStream.iterate();

		SingleOutputStreamOperator<Tuple2<Long, Long>> operatorStream = iterateStream.map(new MapFunction<Long, Tuple2<Long, Long>>() {
			@Override
			public Tuple2<Long, Long> map(Long aLong) throws Exception {
				return new Tuple2<>(aLong, aLong);
			}
		});

		SingleOutputStreamOperator<Long> feedbackDataStream = iterateStream.filter(new FilterFunction<Long>() {
			@Override
			public boolean filter(Long aLong) throws Exception {
				return aLong > 900;
			}
		});


		// 如果这里直接输出是会报错的，Iteration FeedbackTransformation ... does not have any feedback edges.就是迭代流没有边界
		// operatorStream.print("map2");

		iterateStream.closeWith(feedbackDataStream);

		// feedbackDataStream里面的判断条件如果写aLong < 1000: 这里会循环不停止的输出，不断从0到1000;
		// 但是如果改为aLong > 1000：就只会输出一遍
		// 如果aLong > 2000: 同样只会输出一遍
		// 如果aLong > 100: 会不断循环输出大于100 小于1000的值
		// 所以这里的结束条件很重要，否则很容易造成无限循环的情况
		operatorStream.print("map2");

		env.execute("iterate job");
	}
}
