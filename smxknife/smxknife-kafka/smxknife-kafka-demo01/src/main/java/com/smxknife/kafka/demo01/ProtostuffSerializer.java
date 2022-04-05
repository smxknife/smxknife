package com.smxknife.kafka.demo01;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

/**
 * @author smxknife
 * 2020/8/30
 */
public class ProtostuffSerializer implements Serializer<_01_3_CustomObj> {
	@Override
	public void configure(Map<String, ?> map, boolean b) {

	}

	@Override
	public byte[] serialize(String topic, _01_3_CustomObj data) {

		if (data == null) {
			return null;
		}

		Schema<_01_3_CustomObj> schema = RuntimeSchema.getSchema(_01_3_CustomObj.class);
		LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);

		byte[] protostuff = null;
		try {
			protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
		} catch (Exception e) {
			throw new IllegalStateException(e.getMessage(), e);
		} finally {
			buffer.clear();
		}

		return protostuff;
	}

	@Override
	public void close() {

	}
}
