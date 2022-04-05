package com.smxknife.kafka.demo01;

import org.apache.kafka.common.serialization.Serializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.util.Map;

public class _01_3_CustomObjSerializer implements Serializer<_01_3_CustomObj> {

	@Override
	public void configure(Map<String, ?> map, boolean isKey) {
		System.out.println("Serializer configure...");
	}

	@Override
	public byte[] serialize(String topic, _01_3_CustomObj data) {
		System.out.println("Serializer serialize...");
		if (data == null) {
			return null;
		}
		byte[] name, age;

		try {
			if (data.getName() != null) {
				name = data.getName().getBytes("UTF-8");
			} else {
				name = new byte[0];
			}

			int dataAge = data.getAge();
			age = new byte[4];
			age[0] = (byte) ((dataAge >> 24) & 0xFF);
			age[1] = (byte) ((dataAge >> 16) & 0xFF);
			age[2] = (byte) ((dataAge >> 8) & 0xFF);
			age[3] = (byte) (dataAge & 0xFF);

			ByteBuffer byteBuffer = ByteBuffer.allocate(4 + 4 + name.length + age.length);
			byteBuffer.putInt(name.length);
			byteBuffer.put(name);
			byteBuffer.putInt(age.length);
			byteBuffer.put(age);

			return byteBuffer.array();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return new byte[0];
	}

	@Override
	public void close() {
		System.out.println("Serializer close...");
	}
}