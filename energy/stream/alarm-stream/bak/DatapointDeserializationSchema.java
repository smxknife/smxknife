import com.smxknife.energy.common.util.ProtostuffUtil;
import com.smxknife.energy.services.metric.spi.domain.Datapoint;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.streaming.connectors.kafka.KafkaDeserializationSchema;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.util.Objects;

/**
 * @author smxknife
 * 2021/5/17
 */
public class DatapointDeserializationSchema implements KafkaDeserializationSchema<ConsumerRecord<String, Datapoint>> {


	@Override
	public boolean isEndOfStream(ConsumerRecord<String, Datapoint> record) {
		return false;
	}

	@Override
	public ConsumerRecord<String, Datapoint> deserialize(ConsumerRecord<byte[], byte[]> consumerRecord) throws Exception {
		final byte[] keyBytes = consumerRecord.key();
		final byte[] valueBytes = consumerRecord.value();
		String key = null;
		if (Objects.nonNull(keyBytes)) {
			key = new String(keyBytes);
		}
		Datapoint datapoint = null;
		if (Objects.nonNull(valueBytes)) {
			datapoint = ProtostuffUtil.deserialize(Datapoint.class, valueBytes, new Datapoint());
		}

		return new ConsumerRecord<String, Datapoint>(
				consumerRecord.topic(),
				consumerRecord.partition(),
				consumerRecord.offset(),
				key,
				datapoint);
	}

	@Override
	public TypeInformation<ConsumerRecord<String, Datapoint>> getProducedType() {
		return TypeInformation.of(new TypeHint<ConsumerRecord<String, Datapoint>>() {
		});
	}
}
