package com.smxknife.softmarket.menu.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;

@JsonComponent
public class MenuJsonComponent {

	public static class Serializer extends JsonSerializer<Menu> {

		@Override
		public void serialize(Menu menu, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
			jsonGenerator.writeStartObject();
			try {
				jsonGenerator.writeArrayFieldStart("button");
				menu.getButtons().stream().forEach(button -> {
					try {
						jsonGenerator.writeStartObject();
						jsonGenerator.writeStringField("name", button.getName());
						if (!CollectionUtils.isEmpty(button.getSubButtons())) {
							jsonGenerator.writeArrayFieldStart("sub_button");
							button.getSubButtons().stream().forEach(sub -> {
								try {
									writeButton(jsonGenerator, sub);
								} catch (IOException e) {
									e.printStackTrace();
								}
							});
							jsonGenerator.writeEndArray();
						} else {
							writeButton(jsonGenerator, button);
						}
						jsonGenerator.writeEndObject();
					} catch (IOException e) {
						e.printStackTrace();
					}
				});
				jsonGenerator.writeEndArray();
			} catch (IOException e) {
				e.printStackTrace();
			}
			jsonGenerator.writeEndObject();
		}

		public void writeButton(JsonGenerator jsonGenerator, Button button) throws IOException {
			jsonGenerator.writeStartObject();
			if (!StringUtils.isEmpty(button.getName())) {
				jsonGenerator.writeStringField("name", button.getName());
			}
			if (!StringUtils.isEmpty(button.getType().name())) {
				jsonGenerator.writeStringField("type", button.getType().name());
			}
			if (!StringUtils.isEmpty(button.getKey())) {
				jsonGenerator.writeStringField("key", button.getKey());
			}
			if (!StringUtils.isEmpty(button.getUrl())) {
				jsonGenerator.writeStringField("url", button.getUrl());
			}
			if (!StringUtils.isEmpty(button.getMediaId())) {
				jsonGenerator.writeStringField("media_id", button.getMediaId());
			}
			if (!StringUtils.isEmpty(button.getAppId())) {
				jsonGenerator.writeStringField("appid", button.getAppId());
			}
			if (!StringUtils.isEmpty(button.getPagePath())) {
				jsonGenerator.writeStringField("pagepath", button.getPagePath());
			}

//			jsonGenerator.writeArrayFieldStart("sub_button");
//			jsonGenerator.writeEndArray();

			jsonGenerator.writeEndObject();
		}
	}

	public static class Deserializer extends JsonDeserializer<Menu> {

		@Override
		public Menu deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
			// TODO 暂时不需要反序列化
			return null;
		}
	}
}
