package com.smxknfie.softmarket.accesstoken.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.TextNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class AccessTokenJsonComponent {

	static Logger logger = LoggerFactory.getLogger(AccessTokenJsonComponent.class);

	public static class Serializer extends JsonSerializer<AccessToken> {

		@Override
		public void serialize(AccessToken accessToken, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
			jsonGenerator.writeStartObject();
			jsonGenerator.writeStringField("access_token", accessToken.getAccessToken());
			jsonGenerator.writeEndObject();
		}
	}

	public static class Deserializer extends JsonDeserializer<AccessToken> {

		@Override
		public AccessToken deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
			TextNode accessToken = (TextNode) treeNode.get("access_token");
			TreeNode expiresIn = treeNode.get("expires_in");

			if (accessToken == null) {
				logger.error("assess token parser error!");
				return null;
			}

			AccessToken token = new AccessToken();
			token.setAccessToken(accessToken.asText());
			token.setExpiresIn(expiresIn.toString());
			return token;
		}
	}
}
