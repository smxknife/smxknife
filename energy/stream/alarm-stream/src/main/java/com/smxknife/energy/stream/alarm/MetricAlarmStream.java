package com.smxknife.energy.stream.alarm;

import com.smxknife.energy.services.alarm.spi.domain.AlarmRecord;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Properties;

/**
 * @author smxknife
 * 2021/5/17
 */
public class MetricAlarmStream {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "localhost:9092");
		properties.put("group.id","metric-alarm");
		properties.put("auto.offset.reset","earliest");
		properties.put("enable.auto.commit", "false");

		FlinkKafkaConsumer<ConsumerRecord<String, Datapoint>> consumer = new FlinkKafkaConsumer("datapoints", new DatapointDeserializationSchema(), properties);

		final FlinkKafkaProducer producer = new FlinkKafkaProducer("alarmRecords", new AlarmRecordSerializationSchema(), properties, FlinkKafkaProducer.Semantic.EXACTLY_ONCE);

		environment.addSource(consumer)
			.filter(new FilterFunction<ConsumerRecord<String, Datapoint>>() {
				@Override
				public boolean filter(ConsumerRecord<String, Datapoint> record) throws Exception {
					final Datapoint datapoint = record.value();
					return Double.compare(0.5D, datapoint.getValue()) > 0;
				}
			}).map(new MapFunction<ConsumerRecord<String, Datapoint>, AlarmRecord>() {
				@Override
				public AlarmRecord map(ConsumerRecord<String, Datapoint> record) throws Exception {
					final Datapoint datapoint = record.value();

					return null;
				}
		}).addSink(producer);

		//environment.fromElements("flink1").print();

		environment.execute("metric-alarm");
	}
}
