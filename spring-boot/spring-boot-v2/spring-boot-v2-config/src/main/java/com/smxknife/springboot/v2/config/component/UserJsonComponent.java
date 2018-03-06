package com.smxknife.springboot.v2.config.component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import com.smxknife.springboot.v2.config.domain.User;
import javafx.scene.paint.Color;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserJsonComponent {

	public static class Serializer extends JsonSerializer<User> {

		@Override
		public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("color", getColorAsWebColor(user.getColor()));
			jsonGenerator.writeEndObject();
		}
	}

	public static class Deserializer extends JsonDeserializer<User> {

		@Override
		public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
			TextNode color
					= (TextNode) treeNode.get("color");
			return new User(Color.web(color.asText()));
		}
	}

	private static String getColorAsWebColor(Color color) {
		int r = (int) Math.round(color.getRed() * 255.0);
		int g = (int) Math.round(color.getGreen() * 255.0);
		int b = (int) Math.round(color.getBlue() * 255.0);
		return String.format("#%02x%02x%02x", r, g, b);
	}
}
