package com.smxknife.springboot._07_jsonserializable;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author smxknife
 * 2021/8/1
 */
public class StatusJsonSerializable extends JsonSerializer<Integer> {
	@Override
	public void serialize(Integer integer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		switch (integer) {
			case 1:
				jsonGenerator.writeString("One");
				break;
			case 2:
				jsonGenerator.writeString("Two");
				break;
			default:
				jsonGenerator.writeString("Other");
		}
	}
}
