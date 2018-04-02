package com.smxknife.springboot.v2.responsebodyRequestBody.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smxknife.springboot.v2.responsebodyRequestBody.domain.JsonModel;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JsonParseHttpMessageConverter implements HttpMessageConverter<JsonModel> {

	private static List<MediaType> supportMediaTypes = Collections.synchronizedList(new ArrayList<>());

	ObjectMapper objectMapper;

	public JsonParseHttpMessageConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@PostConstruct
	void init() {
		this.getSupportedMediaTypes().add(MediaType.APPLICATION_JSON);
	}

	@Override
	public boolean canRead(Class<?> clazz, MediaType mediaType) {
		return support(clazz) && supportRead(mediaType);
	}

	private boolean supportRead(MediaType mediaType) {
		if (mediaType == null) return true;
		for (MediaType supportMediaType : supportMediaTypes) {
			if (supportMediaType.includes(mediaType)) return true;
		}
		return false;
	}

	private boolean support(Class<?> clazz) {
		return JsonModel.class.isAssignableFrom(clazz);
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return support(clazz) && supportWrite(mediaType);
	}

	private boolean supportWrite(MediaType mediaType) {
		if (mediaType == null || MediaType.ALL.equals(mediaType)) {
			return true;
		}
		for (MediaType supportedMediaType : getSupportedMediaTypes()) {
			if (supportedMediaType.isCompatibleWith(mediaType)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<MediaType> getSupportedMediaTypes() {
		return supportMediaTypes;
	}

	@Override
	public JsonModel read(Class<? extends JsonModel> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		return objectMapper.readValue(inputMessage.getBody(), clazz);
	}

	@Override
	public void write(JsonModel jsonModel, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
		objectMapper.writeValue(outputMessage.getBody(), jsonModel);
	}
}
