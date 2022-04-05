package com.smxknife.flink.ceo.demo.ordertimeout;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.OutputTag;

import java.time.Duration;

/**
 * @author smxknife
 * 2020/8/31
 */
public class OrderTimeout {
	public static void main(String[] args) throws Exception {
		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.setParallelism(1);

		// 订单事件，15分钟内如果没有支付操作，订单失效
		// 检测的是不符合某种事件序列的事件

		KeyedStream<OrderEvent, String> orderEventKeyedStream = env.readTextFile(OrderTimeout.class.getResource("/Orderlog.csv").getPath())
				.map(new MapFunction<String, OrderEvent>() {
					@Override
					public OrderEvent map(String log) throws Exception {
						String[] comp = log.split(",");
						return new OrderEvent(comp[0].trim(), comp[1].trim(), comp[2].trim(), Long.valueOf(comp[3].trim()));
					}
				}).assignTimestampsAndWatermarks(
						WatermarkStrategy.<OrderEvent>forBoundedOutOfOrderness(Duration.ofMinutes(15))
								.withTimestampAssigner((event, processTimestamp) -> event.getTimestamp()))
				.keyBy(event -> event.getOrderId());

		// 定义Pattern
		Pattern<OrderEvent, OrderEvent> payOrderPattern = Pattern.<OrderEvent>begin("createOrder").where(new IterativeCondition<OrderEvent>() {
			@Override
			public boolean filter(OrderEvent orderEvent, Context<OrderEvent> context) throws Exception {
				return orderEvent.getFlag().equals("create");
			}
		}).followedBy("payOrder").where(new IterativeCondition<OrderEvent>() {
			@Override
			public boolean filter(OrderEvent orderEvent, Context<OrderEvent> context) throws Exception {
				return orderEvent.getFlag().equals("pay");
			}
		}).within(Time.minutes(15));

		// 在事件流上应用模式，得到一个PatternStream
		PatternStream<OrderEvent> orderEventPatternStream = CEP.pattern(orderEventKeyedStream, payOrderPattern);

		// 调用select，提取事件序列，超时的事件要做报警提示（采用侧输出流）
		OutputTag<OrderState> timeoutOrderOutput = new OutputTag<OrderState>("timeoutOrder", TypeInformation.of(OrderState.class));

		SingleOutputStreamOperator<OrderState> payOrderStream = orderEventPatternStream.select(timeoutOrderOutput, new OrderTimeoutPatternFunction(), new OrderPatternSelectFunction());

		payOrderStream.print("- normal order");

		payOrderStream.getSideOutput(timeoutOrderOutput).print("☆ timeout order");

		env.execute("order-timeout cep job");

	}
}
