package com.smxknife.energy.common.util;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author smxknife
 * 2021/5/14
 */
public class ProtostuffUtil {

	private static Map<Class, Schema> schemaCache = new ConcurrentHashMap<>();

	public static byte[] serialize(Object data) {
		final Schema schema = schemaCache.computeIfAbsent(data.getClass(), key -> RuntimeSchema.getSchema(data.getClass()));
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

	public static <T>  T deserialize(Class<T> clazz, byte[] bytes, T object) {
		if (Objects.isNull(bytes)) {
			return null;
		}

		final Schema schema = schemaCache.computeIfAbsent(object.getClass(), key -> RuntimeSchema.getSchema(object.getClass()));

		ProtostuffIOUtil.mergeFrom(bytes, object, schema);
		return object;
	}
}
