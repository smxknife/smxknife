package com.smxknife.flink.ceo.demo.loginfail;

import org.apache.flink.api.common.eventtime.SerializableTimestampAssigner;
import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.cep.CEP;
import org.apache.flink.cep.PatternStream;
import org.apache.flink.cep.pattern.Pattern;
import org.apache.flink.cep.pattern.conditions.IterativeCondition;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author smxknife
 * 2020/8/31
 */
public class LoginFail {
	public static void main(String[] args) throws Exception {

		Instant instant = Instant.ofEpochSecond(1511544070);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
		System.out.println(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		Instant instant1 = Instant.ofEpochSecond(1511544071);
		LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
		System.out.println(localDateTime1.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

		StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.setParallelism(1);

		DataStreamSource<String> dataStreamSource = env.readTextFile(LoginFail.class.getResource("/LoginFail.csv").getPath());

		KeyedStream<LoginEvent, String> keyedStream = dataStreamSource.map(new MapFunction<String, LoginEvent>() {
			@Override
			public LoginEvent map(String log) throws Exception {
				String[] component = log.split(",");
				return new LoginEvent(component[0].trim(), component[1].trim(), Long.valueOf(component[2].trim()), component[3]);
			}
		}).assignTimestampsAndWatermarks(WatermarkStrategy.<LoginEvent>forBoundedOutOfOrderness(Duration.ofSeconds(2)).withTimestampAssigner(new SerializableTimestampAssigner<LoginEvent>() {
			@Override
			public long extractTimestamp(LoginEvent event, long l) {
				return event.getTimestamp() * 1000;
			}
		})).keyBy(new KeySelector<LoginEvent, String>() {
			@Override
			public String getKey(LoginEvent loginEvent) throws Exception {
				return loginEvent.getUserId();
			}
		});

		// 定义匹配模式
		Pattern<LoginEvent, LoginEvent> loginEventPattern = Pattern.<LoginEvent>begin("firstFail").where(new IterativeCondition<LoginEvent>() {
			@Override
			public boolean filter(LoginEvent loginEvent, Context<LoginEvent> context) throws Exception {
//				System.out.println("---- " + loginEvent);
				return loginEvent.getFlag().equals("fail");
			}
		}).next("nextFail").where(new IterativeCondition<LoginEvent>() {
			@Override
			public boolean filter(LoginEvent loginEvent, Context<LoginEvent> context) throws Exception {
//				System.out.println("---- " + loginEvent);
				return loginEvent.getFlag().equals("fail");
			}
		}).within(Time.hours(2));

		// 在事件流上应用模式，得到一个PatternStream
		PatternStream<LoginEvent> loginEventPatternStream = CEP.pattern(keyedStream, loginEventPattern);
		// 从PatternStream上应用selectFunction，检出匹配的事件序列
		SingleOutputStreamOperator<Warning> loginFailEventStream = loginEventPatternStream.select(new LoginFailMatch());
		loginFailEventStream.print("login-fail");

		env.execute("login cep job");

	}
}
