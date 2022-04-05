package com.smxknife.kafka.demo01;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Objects;

/**
 * @author smxknife
 * 2020/8/24
 */
public class _01_3_CustomObjDeserializer implements Deserializer<_01_3_CustomObj> {
	@Override
	public void configure(Map<String, ?> map, boolean isKey) {

	}

	@Override
	public _01_3_CustomObj deserialize(String topic, byte[] bytes) {
		if (Objects.isNull(bytes)) {
			return null;
		}

		if (bytes.length < 8) {
			throw new SerializationException("Size of data received by Deserializer is shorter than expected!");
		}

		ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

		int nameLength, ageLength;

		nameLength = byteBuffer.getInt();
		byte[] nameBytes = new byte[nameLength];
		byteBuffer.get(nameBytes);

		ageLength = byteBuffer.getInt();
		byte[] ageBytes = new byte[ageLength];
		byteBuffer.get(ageBytes);

		try {
			String name = new String(nameBytes, "UTF-8");
			int age = ((ageBytes[0] & 0xFF) << 24)
					| ((ageBytes[1] & 0xFF) << 16)
					| ((ageBytes[2] & 0xFF) << 8)
					| (ageBytes[3] & 0xFF);
			return new _01_3_CustomObj(name, age);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void close() {

	}
}
