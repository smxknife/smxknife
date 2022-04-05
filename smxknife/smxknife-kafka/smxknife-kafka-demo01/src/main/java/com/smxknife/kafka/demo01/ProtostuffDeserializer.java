package com.smxknife.kafka.demo01;

import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author smxknife
 * 2020/8/30
 */
public class ProtostuffDeserializer implements Deserializer<_01_3_CustomObj> {
	@Override
	public void configure(Map<String, ?> map, boolean b) {

	}

	@Override
	public _01_3_CustomObj deserialize(String topic, byte[] bytes) {
		if (bytes == null) {
			return null;
		}

		Schema<_01_3_CustomObj> schema = RuntimeSchema.getSchema(_01_3_CustomObj.class);
		_01_3_CustomObj data = new _01_3_CustomObj();

		ProtostuffIOUtil.mergeFrom(bytes, data, schema);
		return data;
	}

	@Override
	public void close() {

	}
}
