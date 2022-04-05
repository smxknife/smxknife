import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;

import java.util.Properties;

/**
 * @author smxknife
 * 2021/5/17
 */
public class MetricAlarmStream {
	public static void main(String[] args) throws Exception {
		final StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
		Properties properties = new Properties();
		properties.setProperty("bootstrap.servers", "192.168.218.23:9092,192.168.218.24:9092,192.168.218.25:9092");
		properties.put("group.id","metric-alarm");
		properties.put("auto.offset.reset","earliest");
		properties.put("enable.auto.commit", "false");

		FlinkKafkaConsumer010<Datapoint> consumer010 = new FlinkKafkaConsumer010("datapoints", new DatapointDeserializationSchema(), properties);

		environment.addSource(consumer010)
				.filter(new FilterFunction<Datapoint>() {
					@Override
					public boolean filter(Datapoint datapoint) throws Exception {
						return true;
					}
				}).print();

		environment.execute("metric-alarm");
	}
}
