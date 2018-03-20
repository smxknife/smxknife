package com.smxknife.softmarket.sendmsg.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.TextNode;
import org.apache.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@JsonComponent
public class SendMsgJsonComponent {
	private static Logger logger = Logger.getLogger(SendMsgJsonComponent.class);

	public static class Serializer extends JsonSerializer<SendMsg> {

		@Override
		public void serialize(SendMsg sendMsg, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
			jsonGenerator.writeStartObject();
			handlerSendType(sendMsg, jsonGenerator);
			handlerSendMsgMedia(sendMsg, jsonGenerator);
			jsonGenerator.writeEndObject();
		}

		private void handlerSendMsgMedia(SendMsg sendMsg, JsonGenerator jsonGenerator) {
			try {
				if (sendMsg.getMsgtype().equals(SendMsg.MsgType.text)) {
					jsonGenerator.writeObjectField(sendMsg.getMediaLabel(), sendMsg.getText());
				} else {
					jsonGenerator.writeObjectField(sendMsg.getMediaLabel(), sendMsg.getMedia());
				}
				jsonGenerator.writeStringField("send_ignore_reprint", String.valueOf(sendMsg.getSend_ignore_reprint()));
				jsonGenerator.writeStringField("msgtype", sendMsg.getMsgtype().name());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void handlerSendType(SendMsg sendMsg, JsonGenerator jsonGenerator) {
			try {
				jsonGenerator.writeStringField("sendType", sendMsg.getSendType().name());
				if (SendMsg.SendType.sendall.equals(sendMsg.getSendType())) {
					jsonGenerator.writeObjectField("filter", sendMsg.getFilter());
				} else {
					if (!Arrays.isNullOrEmpty(sendMsg.getTouser())) {
						jsonGenerator.writeArrayFieldStart("touser");
						jsonGenerator.writeString(Stream.of(sendMsg.getTouser()).collect(Collectors.joining(",")));
						jsonGenerator.writeEndArray();
					}

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static class Deserializer extends JsonDeserializer<SendMsg> {

		@Override
		public SendMsg deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
			SendMsg sendMsg = new SendMsg();
			TreeNode treeNode = jsonParser.getCodec().readTree(jsonParser);
			TextNode sendType = (TextNode) treeNode.get("sendType");
			TreeNode touser = treeNode.get("touser");
			if (sendType.isMissingNode()) {
				logger.error("sendmsg formatter error, missing sendType attr");
				return null;
			}
			if (SendMsg.SendType.sendall.equals(SendMsg.SendType.valueOf(sendType.asText()))) {
				sendMsg.setSendType(SendMsg.SendType.sendall);
				TreeNode filterNode = treeNode.get("filter");
				if (!filterNode.isMissingNode()) {
					SendMsg.Filter filter = sendMsg.new Filter();
					BooleanNode is_to_all = (BooleanNode) filterNode.get("is_to_all");
					if (!is_to_all.isMissingNode()) {
						filter.setIs_to_all(is_to_all.booleanValue());
					}

					ArrayNode tag_idNode = (ArrayNode) filterNode.get("tag_id");
					if (!tag_idNode.isMissingNode()) {
						List<String> tag_id = tag_idNode.findValuesAsText("tag_id", new ArrayList<>());
						System.out.println();
//						filter.setTag_id(Collections.addAll(tag_id));
					}
				}
			} else {
				sendMsg.setSendType(SendMsg.SendType.openid);
			}
			return null;
		}
	}
}
